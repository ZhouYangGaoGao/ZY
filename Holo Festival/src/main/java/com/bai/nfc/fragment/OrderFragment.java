package com.bai.nfc.fragment;

import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.bean.Incomes;
import com.bai.nfc.util.RequestUtil;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.view.SelectBar;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends ListFragment {

    List<Incomes.DetailedsBean> datas = new ArrayList<>();
    int amount = 0;

    @Override
    public CommonAdapter initAdapter() {
        return new CommonAdapter<Incomes.DetailedsBean>(getContext(), datas, R.layout.item_order) {
            @Override
            public void convert(ViewHolder h, Incomes.DetailedsBean i) {
                SelectBar selectBar = h.getView(R.id.sb_order);
                if (i.getGoodsId() == 0) {
                    selectBar.initText(" | | | |总收入 :|" + amount / 100d);
                } else
                    selectBar.initText(i.getGoodsName() + "|" + i.getGoodsAmount() + "|"
                            + (i.getGoodsPrice() == -1 ? "可变" : i.getGoodsPrice() / 100d) + "|"
                            + i.getQuantity() + "|" + i.getQuantityR() + "|" + i.getPriceAll() / 100d);
                if (h.getmPosition() % 2 == 1) {
                    selectBar.setBackgroundColor(0xff666666);
                } else {
                    selectBar.setBackgroundColor(0xff333333);

                }
            }
        };
    }

    @Override
    public void initViewData() {
        topbar.setVisibility(View.GONE);
        refresh.setEnableLoadMore(false);
        refresh.setEnableRefresh(false);
        getData();
    }

    @Override
    public void getData() {
        showLoading();
        RequestUtil.getDetailed(this);
    }

    @Override
    public void onSuccess(String Tag, String value) {
        Incomes orders = JSON.parseObject(value, Incomes.class);
        if (orders != null && orders.getDetaileds() != null) {
            datas.addAll(orders.getDetaileds());
            for (int i = 0; i < datas.size(); i++) {
                amount += datas.get(i).getPriceAll();
            }
            datas.add(new Incomes.DetailedsBean());
            upData();

        }
    }

    @Override
    public void onException(String Tag, String value) {
        Toast.makeText(getActivity(),value,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish(String Tag, String value) {
        disLoading();
    }

}
