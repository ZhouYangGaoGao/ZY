package com.bai.nfc.fragment;

import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.bean.GoodsList;
import com.bai.nfc.util.RequestUtil;
import com.bai.nfc.util.Urls;
import com.orhanobut.hawk.Hawk;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.ClickListener;
import com.zhy.zlib.utils.DialogUtils;
import com.zhy.zlib.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsFragment extends ListFragment {
    List<GoodsList.PageInfoBean.ListBean> datas;
    double amount = 0;

    @Override
    public CommonAdapter initAdapter() {
        return new CommonAdapter<GoodsList.PageInfoBean.ListBean>(getContext(), datas = new ArrayList<>(), R.layout.item_goods) {
            @Override
            public void convert(final ViewHolder h, final GoodsList.PageInfoBean.ListBean i) {
                h.setText(R.id.tv_name, i.getGoodsName());
                h.getView(R.id.tv_price).setClickable(false);
                if (i.getGoodsPrice() > 0)
                    h.setText(R.id.tv_price, i.getGoodsPrice() / 100d + " H");
                else {
                    TextView tvPrice = h.getView(R.id.tv_price);
                    tvPrice.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
                    if (i.getCustomizationPrice() != 0) {
                        h.setText(R.id.tv_price, i.getCustomizationPrice() / 100d + " H");
                    } else {
                        h.setText(R.id.tv_price, "点击编辑");
                    }
                    h.getView(R.id.tv_price).setClickable(true);
                    h.setClick(R.id.tv_price, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DialogUtils.showEditeDialog(getActivity(), "编辑价格", "RMB-HOLO 6.6:1", "确定", new ClickListener() {
                                @Override
                                public void onChange(Object arg) {
                                    int price = (int) arg;
                                    h.setText(R.id.tv_price, price / 100d + " H");
                                    datas.get(h.getmPosition()).setCustomizationPrice(price);
                                }
                            });
                        }
                    });
                }
                h.setText(R.id.tv_count, "" + i.getSelectCount());
                h.setClick(R.id.tv_add, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (i.getGoodsPrice() == -1 && i.getCustomizationPrice() == 0)
                            return;
                        datas.get(h.getmPosition()).setSelectCount(i.getSelectCount() + 1);
                        if (i.getGoodsPrice() == -1) {
                            amount += i.getCustomizationPrice();
                        } else {
                            amount += i.getGoodsPrice();
                        }
                        listener.onChange(amount);
                        upData();
                    }
                });
                h.setClick(R.id.tv_sub, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (i.getSelectCount() != 0) {
                            datas.get(h.getmPosition()).setSelectCount(i.getSelectCount() - 1);

                            if (i.getGoodsPrice() == -1) {
                                amount -= i.getCustomizationPrice();
                            } else {
                                amount -= i.getGoodsPrice();
                            }
                            listener.onChange(amount);
                            upData();
                        }
                    }
                });
            }
        };
    }

    @Override
    public void initViewData() {
        topbar.setVisibility(View.GONE);
        refresh.setEnableLoadMore(false);
        showLoading();
        getData();
    }

    @Override
    public void getData() {
        RequestUtil.goodsList(page, this);
    }

    @Override
    public void onSuccess(String Tag, String value) {
        switch (Tag) {
            case Urls.goodsList:
                GoodsList list = JSON.parseObject(value, GoodsList.class);
                if (list.getPageInfo().getList() != null && list.getPageInfo().getList().size() > 0) {
                    datas.addAll(list.getPageInfo().getList());
                } else {
                    Toast.makeText(getActivity(), "没数据啦", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        getData();

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        listener.onChange(amount = 0);
        datas.clear();
        upData();
        page = 0;
        getData();
    }

    @Override
    public void onFinish(String Tag, String value) {
        finishRefreshAndLoadMore();
        disLoading();
        upData();
    }

    public boolean saveSelected() {
        List<GoodsList.PageInfoBean.ListBean> selected = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getSelectCount() > 0 && datas.get(i).getGoodsPrice() != -1) {
                selected.add(datas.get(i));
            } else if (datas.get(i).getCustomizationPrice() > 0 && datas.get(i).getSelectCount() > 0) {
                selected.add(datas.get(i));
            }
        }
        if (amount > 0 && Hawk.put("selecetGoods", selected)) {
            return Hawk.put("selecetAmount", amount);
        } else {
            Toast.makeText(getContext(), "未选择商品", Toast.LENGTH_SHORT).show();
        }

        return false;
    }


    ClickListener listener;

    public void setOnPriceChangeListener(ClickListener listener) {
        this.listener = listener;
    }

    public static boolean needRefresh;

    @Override
    public void onResume() {
        super.onResume();
        if (needRefresh) {
            needRefresh = false;
            refresh.autoRefresh();
        }

    }

    @Override
    public Drawable setDivider() {
        return new ColorDrawable(0xff666666);
    }
}
