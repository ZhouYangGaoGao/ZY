package com.bai.nfc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends ExtentScreenBaseActivity {
    @BindView(R.id.lv_goods)
    ListView lvGoods;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_WX)
    TextView tvWX;
    @BindView(R.id.tv_ZFB)
    TextView tvZFB;

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
                mIzkcService.showString(tvAmount.getText().toString() + " 请出示付款吗",
                        0, 240, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    @OnClick({R.id.tv_WX, R.id.tv_ZFB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_WX:
                startActivityForResult(new Intent(this, CodeActivity.class).putExtra("payAmount", amount)
                        .putExtra("payWay", "1"), BalanceActivity.PAY);
                finish();
                break;
            case R.id.tv_ZFB:
                startActivityForResult(new Intent(this, CodeActivity.class).putExtra("payAmount", amount)
                        .putExtra("payWay", "2"), BalanceActivity.PAY);
                finish();
                break;
        }
    }
}
