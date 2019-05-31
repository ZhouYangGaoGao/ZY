package com.bai.nfc.fragment;

import android.os.Bundle;
import android.view.View;

import com.bai.nfc.R;

public class MeFragment extends BaseFragment {
    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.fragment_me);
    }

    @Override
    public void initView() {

    }
}
