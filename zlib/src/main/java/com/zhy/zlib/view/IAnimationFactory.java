package com.zhy.zlib.view;

import android.graphics.Point;
import android.view.View;


public interface IAnimationFactory {

    void animateInView(View target, Point point, long duration, AnimationStartListener listener);

    void animateOutView(View target, Point point, long duration, AnimationEndListener listener);

    void animateTargetToPoint(GuideView showcaseView, Point point);

    public interface AnimationStartListener {
        void onAnimationStart();
    }

    public interface AnimationEndListener {
        void onAnimationEnd();
    }
}

