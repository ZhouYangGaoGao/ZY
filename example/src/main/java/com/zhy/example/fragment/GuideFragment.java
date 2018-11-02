package com.zhy.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.example.R;
import com.zhy.zlib.listener.IShowcaseListener;
import com.zhy.zlib.listener.TopListener;
import com.zhy.zlib.utils.GuideUtil;
import com.zhy.zlib.utils.ScreenUtils;
import com.zhy.zlib.view.GuideView;
import com.zhy.zlib.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GuideFragment extends BaseFragment {
    @BindView(R.id.guide_text)
    TextView guideText;
    @BindView(R.id.guide_img)
    ImageView guideImg;
    @BindView(R.id.guide_bottom)
    ImageView guideBottom;
    @BindView(R.id.topbar)
    TopBar topbar;

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.fragment_guide);
    }

    @Override
    public void initView() {
        topbar.setOnTopListener(new TopListener() {
            @Override
            public void lTClick() {
                GuideUtil.guide(guideText, "潮流尖货，点这里开始收割").setShapePadding(-2).setListener(new IShowcaseListener() {
                    @Override
                    public void onShowcaseDisplayed(GuideView showcaseView) {
                        showcaseView.setTriangle(1);//箭头显示位置  0:无   1:左上(默认)  2:右上  3:左下  4:右下
                        //设置位置偏移
                        showcaseView.mContentBox.setPadding(ScreenUtils.dip2px(getContext(), 63), 0, 0, 0);
                    }

                    @Override
                    public void onShowcaseDismissed(GuideView showcaseView) {
                    }
                }).show();
            }

            @Override
            public void tClick() {
                GuideUtil.guide(guideImg, "潮流尖货，点这里开始收割").setListener(new IShowcaseListener() {
                    @Override
                    public void onShowcaseDisplayed(GuideView showcaseView) {
                        showcaseView.setTriangle(0);
                        showcaseView.mContentBox.setPadding(10, 10, 10, 10);
                    }

                    @Override
                    public void onShowcaseDismissed(GuideView showcaseView) {
                    }
                }).withRectangleShape(true).show();
            }

            @Override
            public void rTClick() {
                GuideUtil.guide(guideBottom, "潮流尖货，点这里开始收割").setListener(new IShowcaseListener() {
                    @Override
                    public void onShowcaseDisplayed(GuideView showcaseView) {
                        showcaseView.setTriangle(4);
                        showcaseView.mContentBox.setPadding(0, 0, ScreenUtils.dip2px(getContext(), 50), 0);
                    }

                    @Override
                    public void onShowcaseDismissed(GuideView showcaseView) {
                    }
                }).withRectangleShape().show();
            }
        });
    }
}
