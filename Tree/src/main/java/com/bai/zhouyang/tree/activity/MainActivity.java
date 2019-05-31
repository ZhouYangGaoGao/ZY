package com.bai.zhouyang.tree.activity;

import android.os.Bundle;
import android.view.View;

import com.bai.zhouyang.tree.R;
import com.bai.zhouyang.tree.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_main);
    }

    @Override
    public void initView() {

    }
}
