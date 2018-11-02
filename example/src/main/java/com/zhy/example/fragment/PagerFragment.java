package com.zhy.example.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.example.R;
import com.zhy.zlib.Base.LibConfig;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.AddView;
import com.zhy.zlib.utils.SelecteUtil;
import com.zhy.zlib.view.AutoScrollViewPager;
import com.zhy.zlib.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PagerFragment extends BaseFragment {
    @BindView(R.id.my_viewPager)
    AutoScrollViewPager myViewPager;
    @BindView(R.id.dot_layout)
    LinearLayout dotLayout;
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.gv)
    GridView gv;
    SelecteUtil slu;
    List<String> gifs = new ArrayList<>();

    @Override
    public void initView() {
        List<String> datas = new ArrayList();
        for (int i = 0; i < LibConfig.loading_name.length/2; i++) datas.add(LibConfig.loading_name[i]);
        slu = new SelecteUtil(myViewPager, datas, new AddView() {
            @Override
            public View initView(int index, Object data) {
                TextView textView = new TextView(getActivity());
                textView.setGravity(Gravity.CENTER);
                textView.setText(data.toString());
                textView.setBackgroundColor(0xff888888);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return textView;
            }
        }, dotLayout);
        for (int i = 0; i < LibConfig.loading_name.length; i++) gifs.add(LibConfig.loading_name[i]);
        gv.setAdapter(new CommonAdapter<String>(getActivity(), gifs, R.layout.item_gif) {
            @Override
            public void convert(ViewHolder h, String i) {
                AVLoadingIndicatorView av = h.getView(R.id.av);
                av.setIndicator(i);
            }
        });


    }

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.fragment_three);
    }


}
