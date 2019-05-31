package com.bai.nfc.activity;

import android.os.Bundle;
import android.view.View;

import com.bai.nfc.R;

public class HomeActivity extends BaseActivity {
    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_home);
    }

    @Override
    public void initView() {

    }
}
