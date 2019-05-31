package com.bai.nfc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.bean.OrderDsc;
import com.bai.nfc.util.Constant;
import com.bai.nfc.util.RequestUtil;
import com.bai.nfc.util.Urls;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.ClickListener;
import com.zhy.zlib.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RingOrderDscActivity extends BaseActivity {
    @BindView(R.id.tv_id_time)
    TextView tvIdTime;
    @BindView(R.id.lv_goods)
    ListView lvGoods;
    @BindView(R.id.tv_refun)
    TextView tvRefun;
    @BindView(R.id.tv_amount)
    TextView tvAmount;

    @BindView(R.id.ll_ref)
    LinearLayout ll_ref;

    String id = "";
    double amount = 0;
    String type = "";
    CommonAdapter adapter;
    List<OrderDsc.BuyJournalsBean> datas = new ArrayList<>();

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_ring_dsc);
    }

    @Override
    public void initView() {
        type = getIntent().getStringExtra("type");
        if (type != null && type.equals("dsc")) {
            ll_ref.setVisibility(View.GONE);
        }
        id = getIntent().getStringExtra(Constant.CONSUMPTION_ID);
        tvIdTime.setText("订单号: " + id + "\n\n时间: " + getIntent().getStringExtra("time"));
        RequestUtil.querySalesRecord(id, this);
        showLoading();
        adapter = new CommonAdapter<OrderDsc.BuyJournalsBean>(this, datas, R.layout.item_goods) {
            @Override
            public void convert(final ViewHolder h, final OrderDsc.BuyJournalsBean i) {
                h.setText(R.id.tv_name, i.getGoodsName());
                h.setText(R.id.tv_count, "" + i.getSelectCount());
                List<OrderDsc.BuyJournalsBean.BuyJournalRsBean> buyJournalRsBean = i.getBuyJournalRs();
                int refCount = 0;
                if (buyJournalRsBean != null && buyJournalRsBean.size() > 0) {
                    for (int j = 0; j < buyJournalRsBean.size(); j++) {
                        refCount += buyJournalRsBean.get(j).getQuantity();
                    }
                }
                final int maxCanRef = i.getQuantity() - refCount;

                TextView price = h.getView(R.id.tv_price);
                price.setTextSize(17f);

                price.setText("卖出: " + i.getQuantity() + "\n已退: " + refCount + "\n价格: " + i.getPrice() / 100d + " H");
                if (type != null && type.equals("dsc")) {
                    h.setVisibility(R.id.tv_count, View.GONE);
                    h.setVisibility(R.id.tv_add, View.GONE);
                    h.setVisibility(R.id.tv_sub, View.GONE);
                } else {
                    h.setClick(R.id.tv_add, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (i.getSelectCount() < maxCanRef) {
                                datas.get(h.getmPosition()).setSelectCount(i.getSelectCount() + 1);
                                amount += i.getPrice();
                                tvAmount.setText("总退款:" + amount / 100d + " H");
                                adapter.notifyDataSetChanged();
                            } else
                                Toast.makeText(RingOrderDscActivity.this, "剩余可退数量:" + maxCanRef, Toast.LENGTH_SHORT).show();
                        }
                    });
                    h.setClick(R.id.tv_sub, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (i.getSelectCount() != 0) {
                                datas.get(h.getmPosition()).setSelectCount(i.getSelectCount() - 1);
                                amount -= i.getPrice();
                                tvAmount.setText("总退款:" + amount / 100d + " H");
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        };
        lvGoods.setAdapter(adapter);
    }

    OrderDsc orderDsc;

    @OnClick(R.id.tv_refun)
    public void onViewClicked() {


        List<OrderDsc.BuyJournalsBean> temp = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getSelectCount() > 0) {
                temp.add(datas.get(i));
            }
        }
        if (temp.size() > 0) {
            showLoading();
            RequestUtil.refundHoloCoin(temp, getIntent().getStringExtra(Constant.COUSTOMER_ID), this);
        } else {
            temp.clear();
        }
    }

    @Override
    public void onSuccess(String Tag, String value) {
        switch (Tag) {
            case Urls.querySalesRecord:
                orderDsc = JSON.parseObject(value, OrderDsc.class);
                if (orderDsc != null && orderDsc.getBuyJournals() != null) {
                    datas.addAll(orderDsc.getBuyJournals());
                    adapter.notifyDataSetChanged();
                }
                break;
            case Urls.refundHoloCoin:
                DialogUtils.showDialog(this, "退款成功!\n\n" + tvAmount.getText().toString(), "点击确定返回上一级", "确定", new ClickListener() {
                    @Override
                    public void yes() {
                        finish();
                    }
                });
                break;
        }
    }


    @Override
    public void onFailure(String Tag, String value) {
        DialogUtils.showDialog(this, "数据请求失败!", value, "返回", new ClickListener() {
            @Override
            public void yes() {
                finish();
            }
        });
    }

    @Override
    public void onException(String Tag, String value) {
        DialogUtils.showDialog(this, "数据请求失败!", value, "返回", new ClickListener() {
            @Override
            public void yes() {
                finish();
            }
        });
    }

    @Override
    public void onFinish(String Tag, String value) {
        disLoading();
    }
}
