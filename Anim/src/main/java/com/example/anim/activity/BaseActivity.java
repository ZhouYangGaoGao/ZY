package com.example.anim.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhy.zlib.Base.LibActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends LibActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView(savedInstanceState));
        ButterKnife.bind(this);
        initView();
    }
}
