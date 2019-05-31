package com.bai.nfc.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.activity.RingOrderDscActivity;
import com.bai.nfc.bean.NewOrderList;
import com.bai.nfc.bean.RingOrders;
import com.bai.nfc.util.Constant;
import com.bai.nfc.util.RequestUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.utils.DateUtil;
import com.zhy.zlib.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderListFragment extends ListFragment {
    List<NewOrderList.PageInfoBean.ListBean> datas = new ArrayList<>();

    @Override
    public CommonAdapter initAdapter() {
        return new CommonAdapter<NewOrderList.PageInfoBean.ListBean>(getContext(), datas, R.layout.item_refun) {
            @Override
            public void convert(ViewHolder h, final NewOrderList.PageInfoBean.ListBean i) {
                h.setText(R.id.tv_amount, i.getConsumptionAmount() / 100d + " H");
                h.setText(R.id.tv_id, "订单号:" + i.getConsumptionId().substring(0, 10));
//                h.setText(R.id.tv_time, i.getConsumptionTime());
                h.setText(R.id.tv_time, DateUtil.milliString2String(i.getConsumptionTime(),DateUtil.MINUTE_PATTERN));

                h.setClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), RingOrderDscActivity.class)
                                .putExtra(Constant.CONSUMPTION_ID, i.getConsumptionId())
                                .putExtra("type", "dsc")
                                .putExtra(Constant.COUSTOMER_ID, getActivity().getIntent().getStringExtra(Constant.COUSTOMER_ID))
                                .putExtra("time", DateUtil.milliString2String(i.getConsumptionTime(),DateUtil.MINUTE_PATTERN)));
                    }
                });
            }
        };
    }

    @Override
    public void initViewData() {
        topbar.setVisibility(View.GONE);
        getData();
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page=1;
        datas.clear();
        upData();
        getData();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        getData();
    }

    @Override
    public void getData() {
        showLoading();
        RequestUtil.queryOrderRecord(page, this);
    }

    @Override
    public void onSuccess(String Tag, String value) {
        NewOrderList orders = JSON.parseObject(value, NewOrderList.class);
        if (orders != null && orders.getPageInfo() != null&&orders.getPageInfo().getList()!=null) {
            datas.addAll(orders.getPageInfo().getList());
            upData();
        }
    }

    @Override
    public void onFinish(String Tag, String value) {
        finishRefreshAndLoadMore();
        disLoading();
    }

    @Override
    public int setDividerHeight() {
        return ScreenUtils.dip2px(getContext(), 12);
    }

}
