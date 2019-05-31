package com.bai.nfc.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

import com.bai.nfc.R;
import com.bai.nfc.bean.GoodsList;
import com.bai.nfc.util.TextStyle;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import com.smartdevice.aidl.IZKCService;
import com.zhy.zlib.utils.LogUtils;

public class PayActivity extends ExtentScreenBaseActivity {
    @BindView(R.id.lv_goods)
    ListView lvGoods;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_next)
    TextView tvNext;

    double amount = 0;
    List<GoodsList.PageInfoBean.ListBean> datas = new ArrayList<>();

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_pay);
    }

    @Override
    public void initView() {
        super.initView();
        amount = Hawk.get("selecetAmount");
        tvAmount.setText("总价: " + amount / 100d + " H");
        List<GoodsList.PageInfoBean.ListBean> temp = Hawk.get("selecetGoods");
        if (temp != null && temp.size() > 0)
            datas.addAll(temp);
        lvGoods.setAdapter(new CommonAdapter<GoodsList.PageInfoBean.ListBean>(this, datas, R.layout.item_selected) {
            @Override
            public void convert(ViewHolder h, GoodsList.PageInfoBean.ListBean i) {
                h.setText(R.id.tv_name, i.getGoodsName());
                h.setText(R.id.tv_count, i.getSelectCount() + "个");

                if (i.getGoodsPrice() != -1)
                    h.setText(R.id.tv_price, "单价:" + i.getGoodsPrice() / 100d + " H    共 " + i.getSelectCount() * i.getGoodsPrice() / 100d + " H");
                else if (i.getCustomizationPrice() > 0)
                    h.setText(R.id.tv_price, "单价:" + i.getCustomizationPrice() / 100d + " H    共 " + i.getSelectCount() * i.getCustomizationPrice() / 100d + " H");
            }
        });


    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        startActivityForResult(new Intent(this, BalanceActivity.class).putExtra("payAmount", amount)
                .putExtra("type", BalanceActivity.PAY), BalanceActivity.PAY);
        finish();
    }

    @Override
    public void onServiceConnected() {
        if (mIzkcService != null)
            try {
                cleanScreen();
                int y = 3;
                mIzkcService.showFillColor(0, 0, 480, 35, "#000000");
                mIzkcService.showFillColor(0, 237, 480, 40, "#000000");
                mIzkcService.showString("消费如下", 0, y, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
                y += 3;
                for (int i = 0; i < datas.size() && i < 6; i++) {
                    GoodsList.PageInfoBean.ListBean listBean = datas.get(i);
                    y += 33;
                    mIzkcService.showString(listBean.getGoodsName() +
                                    "   单价:" + (listBean.getGoodsPrice() == -1 ? listBean.getCustomizationPrice() / 100d : listBean.getGoodsPrice() / 100d) +
                                    " H    数量:" + listBean.getSelectCount(),
                            0, y, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
                }
                mIzkcService.showString(tvAmount.getText().toString() + " 将手环靠近左侧支付",
                        0, 240, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
