package com.zhy.example.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.zhy.example.R;
import com.zhy.zlib.utils.SelecteUtil;
import com.zhy.zlib.view.TopBar;

import java.util.List;

import butterknife.BindView;

public class TextFragment extends BaseFragment {

    @Override
    public void initView() {
    }

    @Override
    public View contentView(Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("   ==>  " + FragmentPagerItem.getPosition(getArguments()));
        return textView;
    }

}
