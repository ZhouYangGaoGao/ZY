package com.zhy.example.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhy.example.R;
import com.zhy.zlib.utils.SelecteUtil;
import com.zhy.zlib.view.TopBar;

import java.util.List;

import butterknife.BindView;

public class SelectFragment extends BaseFragment {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.topbar)
    TopBar topbar;

    @Override
    public void initView() {
        final List<TextView> tvs = topbar.selectBar.getTvs();
        topbar.setSelectListener(new SelecteUtil.Onselecte() {
            @Override
            public boolean onselected(View v, int index) {
                text.setText(tvs.get(index).getText());
                return false;
            }
        });
        topbar.selectBar.slu.doSelecte(0);
    }

    @Override
    public View contentView(Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_one, null);
    }

}
