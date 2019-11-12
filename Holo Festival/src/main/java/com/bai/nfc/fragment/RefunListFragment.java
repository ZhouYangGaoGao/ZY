package com.bai.nfc.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.activity.RingOrderDscActivity;
import com.bai.nfc.bean.RingOrders;
import com.bai.nfc.util.Constant;
import com.bai.nfc.util.RequestUtil;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.utils.DateUtil;
import com.zhy.zlib.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class RefunListFragment extends ListFragment {
    List<RingOrders.ConsumptionsBean> datas = new ArrayList<>();

    @Override
    public CommonAdapter initAdapter() {
        return new CommonAdapter<RingOrders.ConsumptionsBean>(getContext(), datas, R.layout.item_refun) {
            @Override
            public void convert(ViewHolder h, final RingOrders.ConsumptionsBean i) {
                h.setText(R.id.tv_amount, i.getConsumptionAmount() / 100d + " ¥");
                h.setText(R.id.tv_id, "订单号:" + i.getConsumptionId().substring(0, 10));
//                h.setText(R.id.tv_time, i.getConsumptionTime());
                h.setText(R.id.tv_time, DateUtil.milliString2String(i.getConsumptionTime(),DateUtil.MINUTE_PATTERN));

                h.setClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), RingOrderDscActivity.class)
                                .putExtra(Constant.CONSUMPTION_ID, i.getConsumptionId())
                                .putExtra(Constant.COUSTOMER_ID, getActivity().getIntent().getStringExtra(Constant.COUSTOMER_ID))
                                .putExtra("time", DateUtil.milliString2String(i.getConsumptionTime(),DateUtil.MINUTE_PATTERN)));
                    }
                });
//                NoScroListView lv=h.getView(R.id.lv_list);
//                lv.setAdapter(new CommonAdapter<String>(getContext(),datas,R.layout.item_refun_list) {
//                    @Override
//                    public void convert(ViewHolder h, String i) {
//
//                    }
//                });
            }
        };
    }

    @Override
    public void initViewData() {
        refresh.setEnableRefresh(false);
        refresh.setEnableLoadMore(false);
        topbar.setVisibility(View.GONE);
        getData();
    }

    @Override
    public void getData() {
        showLoading();
        RequestUtil.queryOrderByMIdAndHId(getActivity().getIntent().getStringExtra(Constant.HAND_RING_ID), this);
    }

    @Override
    public void onSuccess(String Tag, String value) {
        RingOrders orders = JSON.parseObject(value, RingOrders.class);
        if (orders != null && orders.getConsumptions() != null) {
            datas.addAll(orders.getConsumptions());
            upData();
        }
    }

    @Override
    public void onFinish(String Tag, String value) {
        disLoading();
    }

    @Override
    public int setDividerHeight() {
        return ScreenUtils.dip2px(getContext(), 12);
    }

}
