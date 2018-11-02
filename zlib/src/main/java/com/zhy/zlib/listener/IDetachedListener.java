package com.zhy.zlib.listener;


import com.zhy.zlib.view.GuideView;

public interface IDetachedListener {
    void onShowcaseDetached(GuideView showcaseView, boolean wasDismissed);
}
