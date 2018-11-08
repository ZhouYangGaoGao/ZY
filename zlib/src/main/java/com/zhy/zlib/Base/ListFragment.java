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

/**
 * ListView GridView Fragment 封装类
 */
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

    /**
     * 初始化
     */
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

    /**
     * 给 listView 添加头部
     *
     * @return
     */
    public View addHearder() {
        return new View(getContext());
    }

    /**
     * 初始化适配器
     *
     * @return
     */
    abstract public CommonAdapter initAdapter();

    /**
     * 初始化 View数据
     */
    abstract public void initViewData();

    /**
     * 设置列表类型 true:GridView   false:ListView   (默认是 ListView)
     *
     * @return
     */
    public boolean isGrid() {
        return false;
    }

    /**
     * 设置 GridView 的列数
     *
     * @return
     */
    public int numColumns() {
        return 2;
    }

    /**
     * 通知适配器更新数据
     */
    public void upData() {
        adapter.notifyDataSetChanged();
    }

    /**
     * 结束加载刷新
     */
    public void finishRefreshAndLoadMore() {
        finishRefreshAndLoadMore(0);
    }

    /**
     * 结束加载刷新
     *
     * @param delayed 延迟毫秒数
     */
    public void finishRefreshAndLoadMore(int delayed) {
        refresh.finishRefresh(delayed);
        refresh.finishLoadMore(delayed);
    }

    /**
     * 给 listView 或 GridView 设置无数据显示的 View
     *
     * @return
     */
    public View addEmptyView() {
        return new View(getContext());
    }

    /**
     * 加载数据
     */
    abstract public void getData();

    /**
     * 数据刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
    }

    /**
     * 数据加载更多
     *
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
    }
}
