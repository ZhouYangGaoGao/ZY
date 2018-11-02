package com.zhy.zlib.listener;


import com.zhy.zlib.view.GuideView;

public interface IShowcaseListener {
    void onShowcaseDisplayed(GuideView showcaseView);
    void onShowcaseDismissed(GuideView showcaseView);
}
