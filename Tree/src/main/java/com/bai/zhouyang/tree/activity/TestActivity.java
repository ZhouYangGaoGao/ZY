package com.bai.zhouyang.tree.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;

import com.bai.zhouyang.tree.R;
import com.bai.zhouyang.tree.adapter.ScrollablePanelAdapter;
import com.bai.zhouyang.tree.bean.CellInfo;
import com.bai.zhouyang.tree.bean.Place;
import com.bai.zhouyang.tree.util.Constant;
import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.orhanobut.hawk.Hawk;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class TestActivity extends BaseActivity {
    @BindView(R.id.scrollView)
    ScrollablePanel scrollablePanel;
    ScrollablePanelAdapter scrollablePanelAdapter;
    Place place;
    Map<String, Map<String, Map<String, Object>>> rows;

    @Override
    public View contentView(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        return getView(R.layout.activity_test);
    }

    MyHandler handler;

    @Override
    public void initView() {

        showLoading();
        handler = new MyHandler(this);
        new Thread(new Runnable() {
            public void run() {
                generateTestData();
            }
        }).start();
    }

    static class MyHandler extends Handler {
        WeakReference<Activity> mWeakReference;

        public MyHandler(TestActivity activity) {
            mWeakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final TestActivity activity = (TestActivity) mWeakReference.get();
            if (activity != null) {
                activity.scrollablePanelAdapter.setOrdersList(activity.rows);
                activity.scrollablePanel.setPanelAdapter(activity.scrollablePanelAdapter);
                activity.disLoading();
            }
        }
    }

    private void generateTestData() {
        place = Hawk.get(Constant.PLACE);
        scrollablePanelAdapter = new ScrollablePanelAdapter();
        scrollablePanelAdapter.setPlace(place, scrollablePanel);
        List<CellInfo> rowInfoList = new ArrayList<>();
        for (int i = 0; i < place.getHeight(); i++) {
            CellInfo rowInfo = new CellInfo();
            rowInfo.setContent("" + i);
            rowInfoList.add(rowInfo);
        }
        scrollablePanelAdapter.setRowInfoList(rowInfoList);
        List<CellInfo> cellInfoList = new ArrayList<>();
        for (int i = 0; i < place.getWidth(); i++) {
            CellInfo cellInfo = new CellInfo();
            String content = i + "";
            cellInfo.setContent(content);
            cellInfoList.add(cellInfo);
        }
        scrollablePanelAdapter.setColumnInfoList(cellInfoList);

        Map<String, Map<String, Map<String, Object>>> tmpList = Hawk.get(Constant.PLACE+ place.getPlaceId());
        if (tmpList != null) {
            rows = tmpList;
        } else {
            rows = new HashMap<>();
        }
        handler.sendEmptyMessage(0);
    }
}
