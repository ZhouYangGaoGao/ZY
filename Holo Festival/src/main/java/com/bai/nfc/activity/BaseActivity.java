package com.bai.nfc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.zhy.zlib.Base.LibActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends LibActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView(savedInstanceState));
        ButterKnife.bind(this);
        initView();
        setLoadingMessage("加载中");
    }

    @Override
    public void onFailure(String Tag, String value) {
        Toast.makeText(this,"网络连接错误",Toast.LENGTH_SHORT).show();
    }
}
