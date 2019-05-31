package com.zhy.example.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhy.example.R;
import com.zhy.zlib.adapter.MyPagerAdapter;
import com.zhy.zlib.utils.GlideApp;
import com.zhy.zlib.utils.LogUtils;
import com.zhy.zlib.utils.ScreenUtils;

import java.util.Timer;
import java.util.TimerTask;
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class HaHaProgressbar extends RelativeLayout {

    private ImageView gif, cover, block,fire;
    private float r = 0, mMax = 10000;
    private Timer timer;
    private TimerTask timerTask;
    private int mProgress = 10000;
    private int interval = 10;//毫秒

    public HaHaProgressbar(Context context) {
        super(context);
        initView(context);

    }

    public HaHaProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HaHaProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        View view = inflate(context, R.layout.layout_progress, null);
        gif = view.findViewById(R.id.gif);
        cover = view.findViewById(R.id.cover);
        block = view.findViewById(R.id.block2);
        fire = view.findViewById(R.id.fire);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        GlideApp.with(context).asGif().load(R.drawable.progressred).fitCenter().into(gif);
        GlideApp.with(context).asGif().load(R.drawable.fire).fitCenter().into(fire);
        measure(w, h);
        coverLayoutParams = new RelativeLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
//        coverLayoutParams.setMargins(ScreenUtils.dip2px(getContext(), -7.5f), 0, 0, 0);
        coverLayoutParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(view, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    int emptyWith = 0;
    RelativeLayout.LayoutParams coverLayoutParams;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setProgress(int progress) {
        LogUtils.e("==progress==", "  " + progress);
        if (progress >= 0 && progress <= mMax) {
            emptyWith = (int) ((getMeasuredWidth() - ScreenUtils.dip2px(getContext(), 5f)) - (progress * r));
            LogUtils.e("==emptyWith==", "  " + emptyWith);
//            if (emptyWith >= ScreenUtils.dip2px(getContext(), 7.5f)) {
                coverLayoutParams.width = emptyWith;
                cover.setLayoutParams(coverLayoutParams);
//            } else {


//                while (emptyWith < ScreenUtils.dip2px(getContext(), 7.5f)) {
//                    setProgress(mProgress -= interval);
//                }
//            }
        }

    }

    /**
     * progressblue   or  progressred
     *
     * @param imgRsc
     */
    public void setImgRsc(int imgRsc) {
        GlideApp.with(getContext()).asGif().load(imgRsc).fitCenter().into(gif);
    }

    /**
     * 设置最大值
     *
     * @param max
     */
    public void setMax(int max) {
        stop();
        mMax = max * 1000;
        interval = max;
        mProgress = (int) mMax;
        setProgress(mProgress);
        block.setVisibility(GONE);
        cover.setVisibility(GONE);
        fire.setVisibility(GONE);
//        start();
    }

    public void start() {
        block.setVisibility(VISIBLE);
        cover.setVisibility(VISIBLE);
        GlideApp.with(getContext()).asGif().load(R.drawable.progressblue).fitCenter().into(gif);
        r = (getMeasuredWidth() - ScreenUtils.dip2px(getContext(), 5f)) / mMax;
        stop();
        timer = new Timer();
        timer.schedule(timerTask = new TimerTask() {
            @Override
            public void run() {
                ((Activity) (getContext())).runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void run() {

                        if (mProgress >= interval) {
                            setProgress(mProgress -= interval);
                        } else {
                            block.setVisibility(GONE);
                            fire.setVisibility(GONE);
                            stop();
                        }
                    }
                });
            }
        }, 0, interval);
    }

    public void stop() {
        if (timer != null) {
            GlideApp.with(getContext().getApplicationContext()).load(R.drawable.pro_blue).fitCenter().into(gif);
            timer.cancel();
            timer.purge();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

}
