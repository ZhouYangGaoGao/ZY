package com.zhy.zlib.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.zlib.Base.LibConfig;
import com.zhy.zlib.R;
import com.zhy.zlib.listener.TopListener;
import com.zhy.zlib.utils.SelecteUtil;


/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/26 下午3:07
 * 用途 :通用topbar
 */
public class TopBar extends RelativeLayout {
    public SelectBar mSelectBar;
    public ImageView mLeftImg1, mLeftImg2, mRightImg1, mRightImg2;
    public TextView mLeftText, mCenterText, mRightText;
    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LinearLayout oldBar = new LinearLayout(context);
        if (getBackground() == null)
            setBackgroundColor(LibConfig.colorTheme);
        final Drawable l1, l2, r1, r2, c, selectTline;
        float ts, iw, selectts, selecttsUn;
        LinearLayout.LayoutParams imglp, tvlp, ctvlp;
        int tc, selecttc, selecttcun;
        String lt, rt, ct;
        final boolean isLongLine, isFinish;
        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar);
        selectTline = t.getDrawable(R.styleable.TopBar_SelectTLineSrc);
        selectts = t.getDimension(R.styleable.TopBar_SelectTsize, sp2px(17));//默认已选文字大小
        selecttsUn = t.getDimension(R.styleable.TopBar_SelectTsizeUn, sp2px(15));//默认未选文字大小
        selecttc = t.getColor(R.styleable.TopBar_SelectTcolor, 0xffffffff);
        selecttcun = t.getColor(R.styleable.TopBar_SelectTcolorUn, 0x80ffffff);
        l1 = t.getDrawable(R.styleable.TopBar_L1src);
        l2 = t.getDrawable(R.styleable.TopBar_L2src);
        c = t.getDrawable(R.styleable.TopBar_CSrc);
        r1 = t.getDrawable(R.styleable.TopBar_R1src);
        r2 = t.getDrawable(R.styleable.TopBar_R2src);
        lt = t.getString(R.styleable.TopBar_LT);
        rt = t.getString(R.styleable.TopBar_RT);
        ct = t.getString(R.styleable.TopBar_CT);
        ts = t.getDimension(R.styleable.TopBar_Tsize, sp2px(18));
        iw = t.getDimension(R.styleable.TopBar_ImgWidth, dip2px(22));
        tc = t.getColor(R.styleable.TopBar_Tcolor, 0xffffffff);
        isLongLine = t.getBoolean(R.styleable.TopBar_SelectLongLine, false);
        isFinish = t.getBoolean(R.styleable.TopBar_isFinish, false);
        t.recycle();

        final LinearLayout left, leftClick, right, center;

        ctvlp = new LinearLayout.LayoutParams(0, dip2px(48), 1);
        tvlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(48));
        tvlp.setMargins(dip2px(4), 0, dip2px(4), 0);
        imglp = new LinearLayout.LayoutParams((int) iw, (int) iw);
        imglp.setMargins(dip2px(6), 0, dip2px(6), 0);
        left = new LinearLayout(context);
        leftClick = new LinearLayout(context);
        left.setPadding(dip2px(4), 0, 0, 0);
        left.setGravity(Gravity.START + Gravity.CENTER_VERTICAL);
        right = new LinearLayout(context);
        right.setGravity(Gravity.END + Gravity.CENTER_VERTICAL);
        right.setPadding(0, 0, dip2px(4), 0);
        center = new LinearLayout(context);
        center.setGravity(Gravity.CENTER);


        if (l1 != null) {
            mLeftImg1 = new ImageView(context);
            mLeftImg1.setImageDrawable(l1);
            left.addView(mLeftImg1, imglp);
            mLeftImg1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTopListener != null)
                        mTopListener.l1Click();
                }
            });
        } else if (isFinish) {
            mLeftImg1 = new ImageView(context);
            mLeftImg1.setImageResource(R.drawable.back);
            left.addView(mLeftImg1, imglp);
            mLeftImg1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                }
            });
        }
        if (lt != null) {
            String ltt[] = lt.split("\\|");
            if (ltt.length > 1) {
                mSelectBar = new SelectBar(context);
                for (int i = 0; i < ltt.length; i++) {
                    if (i == 0)
                        mSelectBar.addText(ltt[0], selectts, selecttc, selectTline, true, isLongLine, false);
                    else
                        mSelectBar.addText(ltt[i], selecttsUn, selecttcun, selectTline, false, isLongLine, false);
                }
                mSelectBar.setSelect(selecttc, selecttcun, selectts, selecttsUn);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, dip2px(47), 1);
                lp.setMargins(dip2px(6), 0, dip2px(6), 0);
                left.addView(mSelectBar, lp);
            } else {
                mLeftText = new TextView(context);
                mLeftText.setText(lt);
                mLeftText.setSingleLine();
                mLeftText.setGravity(Gravity.CENTER);
                mLeftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ts);
                mLeftText.setTextColor(tc);
                left.addView(mLeftText, tvlp);
                mLeftText.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isFinish) {
                            ((Activity) context).finish();
                        } else if (mTopListener != null)
                            mTopListener.lTClick();
                    }
                });
            }
        }
        if (l2 != null) {
            mLeftImg2 = new ImageView(context);
            mLeftImg2.setImageDrawable(l2);
            left.addView(mLeftImg2, imglp);
            mLeftImg2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTopListener != null)
                        mTopListener.l2Click();
                }
            });
        }

        if (r2 != null) {
            mRightImg2 = new ImageView(context);
            mRightImg2.setImageDrawable(r2);
            right.addView(mRightImg2, imglp);
            mRightImg2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTopListener != null)
                        mTopListener.r2Click();
                }
            });
        }
        if (rt != null) {
            String rtt[] = rt.split("\\|");
            if (rtt.length > 1) {
                mSelectBar = new SelectBar(context);
                for (int i = 0; i < rtt.length; i++) {
                    if (i == 0)
                        mSelectBar.addText(rtt[0], selectts, selecttc, selectTline, true, isLongLine, false);
                    else
                        mSelectBar.addText(rtt[i], selectts, selecttcun, selectTline, false, isLongLine, false);
                }
                mSelectBar.setSelect(selecttc, selecttcun, selectts, selecttsUn);
                right.addView(mSelectBar, new LinearLayout.LayoutParams(0, dip2px(47), 1));
            } else {
                mRightText = new TextView(context);
                mRightText.setText(rt);
                mRightText.setGravity(Gravity.CENTER);
                mRightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ts);
                right.addView(mRightText, tvlp);
                mRightText.setTextColor(tc);
                mRightText.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTopListener != null)
                            mTopListener.rTClick();
                    }
                });
            }
        }
        if (r1 != null) {
            mRightImg1 = new ImageView(context);
            mRightImg1.setImageDrawable(r1);
            right.addView(mRightImg1, imglp);
            mRightImg1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTopListener != null)
                        mTopListener.r1Click();
                }
            });
        }
        if (c != null) {
            ImageView C = new ImageView(context);
            C.setImageDrawable(c);
            center.addView(C, imglp);
            C.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTopListener != null)
                        mTopListener.cClick();
                }
            });

        }


        if (ct != null) {
            String tt[] = ct.split("\\|");
            if (tt.length > 1) {
                mSelectBar = new SelectBar(context);
                for (int i = 0; i < tt.length; i++) {
                    if (i == 0)
                        mSelectBar.addText(tt[0], selectts, selecttc, selectTline, true, isLongLine, false);
                    else
                        mSelectBar.addText(tt[i], selectts, selecttcun, selectTline, false, isLongLine, false);
                }
                mSelectBar.setSelect(selecttc, selecttcun, selectts, selecttsUn);
                center.addView(mSelectBar, ctvlp);
            } else {
                mCenterText = new TextView(context);
                mCenterText.setText(ct);
                mCenterText.setGravity(Gravity.CENTER);
                mCenterText.setTextColor(tc);
                mCenterText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ts);
                center.addView(mCenterText, tvlp);
                mCenterText.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTopListener != null)
                            mTopListener.tClick();
                    }
                });
            }
        }
        if (mSelectBar != null)
            mSelectBar.setBackgroundColor(0x00000000);
        oldBar.setGravity(Gravity.CENTER_VERTICAL);

        leftClick.addView(left, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(48)));

        int leftCount = left.getChildCount(), centerCount = center.getChildCount(), rightCount = right.getChildCount();


        if (leftCount > 0 && centerCount == 0 && rightCount == 0) {
            oldBar.addView(leftClick, ctvlp);
        }
        if (leftCount >= 0 && centerCount > 0) {
            oldBar.addView(leftClick, ctvlp);
            oldBar.addView(center, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(48)));
            oldBar.addView(right, ctvlp);
        }

        if (leftCount >= 0 && centerCount == 0 && rightCount > 0) {
            oldBar.addView(leftClick, ctvlp);
            oldBar.addView(right, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(48)));
        }

        addView(oldBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(48)));
    }

    private TopListener mTopListener;

    //设置点击事件监听器
    public void setOnTopListener(TopListener listener) {
        this.mTopListener = listener;
    }


    private int dip2px(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public void setTextSize(TextView textView, float textsize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sp2px(textsize));
    }

    public void setSelectListener(SelecteUtil.Onselecte selectListener){
        if (mSelectBar !=null){
            mSelectBar.setOnselecte(selectListener);
        }
    }

}


