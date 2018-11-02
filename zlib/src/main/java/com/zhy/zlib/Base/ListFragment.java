package com.zhy.zlib.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.zlib.R;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.view.TopBar;

public abstract class ListFragment extends LibFragment implements OnRefreshLoadMoreListener {
    public ListView listview;
    public LinearLayout emptyView;
    public SmartRefreshLayout refresh;
    public TopBar topbar;
    public CommonAdapter adapter;
    public int page = 0;
    public GridView gridView;
    boolean isGrid = false;

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.topbar_listview);
    }

    @Override
    public void initView() {
        listview = (ListView) findViewById(R.id.listview);
        gridView = (GridView) findViewById(R.id.gridview);
        emptyView = (LinearLayout) findViewById(R.id.empty_view);
        refresh = (SmartRefreshLayout) findViewById(R.id.refresh);
        topbar = (TopBar) findViewById(R.id.topbar);
        initAdapter();
        if (isGrid) {
            listview.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            gridView.setAdapter(adapter);
            gridView.setEmptyView(emptyView);
        } else {
            gridView.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
            listview.setAdapter(adapter);
            listview.setEmptyView(emptyView);
            listview.addFooterView(new View(getContext()));
            listview.addHeaderView(new View(getContext()));
        }

        refresh.setOnRefreshLoadMoreListener(this);
        initViewData();
    }
    public void setGrid(boolean grid) {
        isGrid = grid;
    }

    abstract public void initAdapter();

    abstract public void initViewData();

    abstract public void getData();

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
    }
}
