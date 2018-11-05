package com.zhy.example.fragment;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.example.R;
import com.zhy.zlib.Base.LibConfig;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.TopListener;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends ListFragment {

    List<String> datas = new ArrayList<>();


    @Override
    public void initAdapter() {
        setGrid(true);
        adapter = new CommonAdapter<String>(getContext(), datas, R.layout.item_gridfragment) {
            @Override
            public void convert(ViewHolder h, String i) {
                AVLoadingIndicatorView view = h.getView(R.id.avl);
                view.setIndicator(i);
                h.setText(R.id.avl_name, i);
            }
        };
    }

    @Override
    public void initViewData() {
        topbar.CT.setText("ListView");
        topbar.LT.setText("show");
        topbar.L1.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
        topbar.setOnTopListener(new TopListener() {
            @Override
            public void lTClick() {
                showLoading();
            }
        });
    }

    @Override
    public void getData() {
        datas.add(LibConfig.loading_name[page]);
        onFinish("getData", "onFinish");
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        if (page < LibConfig.loading_name.length) {
            getData();
        } else {
            showToast("到底了");
            onFinish("到底了", "");
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        datas.clear();
        page = 1;
        getData();
    }

    @Override
    public void onFinish(String Tag, String value) {
        refresh.finishLoadMore(300);
        refresh.finishRefresh(300);
        adapter.notifyDataSetChanged();
    }
}
