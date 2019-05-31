package com.bai.nfc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.bean.Classify;
import com.bai.nfc.fragment.GoodsFragment;
import com.bai.nfc.util.Constant;
import com.bai.nfc.util.RequestUtil;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.ClickListener;
import com.zhy.zlib.listener.TopListener;
import com.zhy.zlib.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.lv_type)
    ListView lvType;
    GoodsFragment fgHome;


    List<Classify.ClassifiesBean> types = new ArrayList<>();
    @BindView(R.id.tv_next)
    TextView tvNext;

    @BindView(R.id.tv_amount)
    TextView tvAmount;

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_goods);
    }

    @Override
    public void initView() {
        fgHome = (GoodsFragment) getSupportFragmentManager().findFragmentById(R.id.fg_home);
        RequestUtil.classifyList((String) Hawk.get(Constant.MERCHAN_ID), this);


        fgHome.setOnPriceChangeListener(new ClickListener() {
            @Override
            public void onChange(Object arg) {
                double price = (double) arg;
                tvAmount.setText("总价格: " + price / 100 + " H");
            }
        });


        topbar.setOnTopListener(new TopListener() {
            @Override
            public void rTClick() {
                startActivity(new Intent(GoodsActivity.this, BalanceActivity.class).putExtra("type",0));
            }
        });
    }


    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        if (fgHome.saveSelected()) {
            startActivity(new Intent(this, PayActivity.class));
        }
    }

    int index = 0;

    @Override
    public void onSuccess(String Tag, String value) {
        Classify classify = JSON.parseObject(value, Classify.class);
        if (classify.getClassifies() != null) {
            types.addAll(classify.getClassifies());
            lvType.setAdapter(new CommonAdapter<Classify.ClassifiesBean>(this, types, R.layout.item_types) {
                @Override
                public void convert(final ViewHolder h, Classify.ClassifiesBean i) {
                    h.setText(R.id.tv_name, i.getClassifyName());
                    if (h.getmPosition() == index) {
                        h.getConvertView().setBackgroundColor(0xffffffff);
                    }
                    h.setClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index=h.getmPosition();
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fgHome.onResume();
    }
}
