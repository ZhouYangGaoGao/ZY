package com.zhy.example.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhy.example.R;
import com.zhy.example.fragment.GuideFragment;
import com.zhy.example.fragment.ListViewFragment;
import com.zhy.example.fragment.PagerFragment;
import com.zhy.example.fragment.SelectFragment;
import com.zhy.example.fragment.OtherFragment;
import com.zhy.zlib.view.SelectBar;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.selectbar)
    SelectBar selectbar;

    @Override
    public void initView() {
        selectbar.withViewPager(viewpager, getSupportFragmentManager(),
                new SelectFragment(), new ListViewFragment(), new PagerFragment(),
                new GuideFragment(),new OtherFragment())
                .setImgResouce(R.drawable.shape_dot_d8d8d8, R.drawable.shape_dot_d8d8d8,
                        R.drawable.shape_dot_d8d8d8, R.drawable.shape_dot_d8d8d8,
                        R.drawable.shape_dot_d8d8d8, R.drawable.shape_dot_blue,
                        R.drawable.shape_dot_blue,R.drawable.shape_dot_blue,
                        R.drawable.shape_dot_blue, R.drawable.shape_dot_blue).doSelecte(0);
    }

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_main);
    }

}
