package com.bai.nfc.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.bai.nfc.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhy.zlib.Base.LibConfig;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.TopListener;
import com.zhy.zlib.utils.OKUtils;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends BaseFragment {


    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.fragment_find);
    }

    @Override
    public void initView() {

    }
}
