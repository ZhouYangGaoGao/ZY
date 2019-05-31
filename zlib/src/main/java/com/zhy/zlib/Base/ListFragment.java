package com.zhy.zlib.Base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.zlib.R;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.utils.ScreenUtils;
import com.zhy.zlib.view.TopBar;

/**
 * ListView GridView Fragment 封装类
 */
public abstract class ListFragment extends LibFragment implements OnRefreshLoadMoreListener {
    /**
     * 刷新加载
     */
    protected SmartRefreshLayout refresh;
    /**
     * 顶部控件
     */
    public TopBar topbar;
    /**
     * listView 和 GridView 的适配器
     */
    private CommonAdapter adapter;
    /**
     * 页码
     */
    public int page = 1;

    /**
     * 设置当前页面跟布局
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.topbar_listview);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        ListView listview = (ListView) findViewById(R.id.listview);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        LinearLayout emptyView = (LinearLayout) findViewById(R.id.empty_view);
        refresh = (SmartRefreshLayout) findViewById(R.id.refresh);
        refresh.setEnableAutoLoadMore(false);
        refresh.setRefreshFooter(new ClassicsFooter(getContext()));
        refresh.setPrimaryColorsId(R.color.colorTheme, R.color.colorWhite);
        topbar = (TopBar) findViewById(R.id.topbar);
        emptyView.addView(addEmptyView());
        emptyView.setGravity(17);
        if (isGrid()) {
            listview.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            gridView.setNumColumns(numColumns());
            gridView.setVerticalSpacing(setVerticalSpacing());
            gridView.setHorizontalSpacing(setHorizontalSpacing());
            gridView.setAdapter(adapter = initAdapter());
            gridView.setEmptyView(emptyView);
        } else {
            gridView.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
            listview.setAdapter(adapter = initAdapter());
            listview.setEmptyView(emptyView);
            listview.addFooterView(addFooter());
            listview.addHeaderView(addHearder());
            listview.setDivider(setDivider());
            listview.setDividerHeight(setDividerHeight());
        }

        refresh.setOnRefreshLoadMoreListener(this);
        initViewData();
    }

    public int setDividerHeight() {
        return ScreenUtils.dip2px(getContext(), 1);
    }

    public Drawable setDivider() {
        return new ColorDrawable(0x00ffffff);
    }

    /**
     * 给 listView 添加尾部
     *
     * @return
     */
    public View addFooter() {
        return new View(getContext());
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

    public int setVerticalSpacing(){
        return 10;
    }

    public int setHorizontalSpacing(){
        return 10;
    }
}
