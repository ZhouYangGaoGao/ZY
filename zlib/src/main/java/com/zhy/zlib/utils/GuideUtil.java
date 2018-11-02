package com.zhy.zlib.utils;

import android.app.Activity;
import android.view.View;

import com.zhy.zlib.view.GuideView;

public class GuideUtil {
    public static GuideView.Builder guide(View view, String content) {
        GuideView.resetAll(view.getContext().getApplicationContext());

        return new GuideView.Builder((Activity) view.getContext())
                .setTarget(view)
                .setContentText(content)
                .setDismissOnTouch(true)
                .setMaskColour(0x90000000)
                .singleUse(content.replace("\n", ""));
    }
}
