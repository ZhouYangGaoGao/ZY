package com.zhy.example.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.zhy.example.BaseApplication;
import com.zhy.example.R;
import com.zhy.example.view.GradientTextView;
import com.zhy.example.view.HaHaProgressbar;
import com.zhy.zlib.utils.LogUtils;
import com.zhy.zlib.utils.SelecteUtil;
import com.zhy.zlib.view.SelectBar;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class OtherFragment extends BaseFragment {

    @BindView(R.id.viewpagertab)
    SmartTabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.down)
    TextView down;
    @BindView(R.id.up)
    TextView up;
    @BindView(R.id.chang)
    GradientTextView chang;
    @BindView(R.id.hahaprogress)
    HaHaProgressbar hahaprogress;
//    @BindView(R.id.sifenzhiyi)
//    ImageView sifenzhiyi;
    @BindView(R.id.ivRead)
    ImageView ivRead;
    @BindView(R.id.ivGo)
    ImageView ivGo;
    @BindView(R.id.selectbar)
    SelectBar selectbar;
    final Animation inFromLeft = AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.slide_in_from_left),
            inFromRight = AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.slide_in_from_right),
            outToLeft = AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.slide_out_to_left),
            outToRight = AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.slide_out_to_right);

    @Override
    public View contentView(Bundle savedInstanceState) {
        LogUtils.e("==contentView=", "contentView");
        return getView(R.layout.fragment_other);
    }

    AnimatorSet animatorSetsuofang;//组合动画

    @SuppressLint("WrongConstant")
    @Override
    public void initView() {
        LogUtils.e("==initView=", "initView");
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getChildFragmentManager(),
                FragmentPagerItems.with(getContext())
                        .add("热门", TextFragment.class)
                        .add("推荐", TextFragment.class)
                        .add("新闻", TextFragment.class)
                        .add("视频", TextFragment.class)
                        .add("军事", TextFragment.class)
                        .add("历史", TextFragment.class)
                        .add("奇闻异事", TextFragment.class)
                        .add("生活", TextFragment.class)
                        .add("两性", TextFragment.class)
                        .add("美女", TextFragment.class)
                        .add("其他", TextFragment.class)
                        .create());

        LogUtils.e("==adapter=", adapter.getCount() + "");
        viewpager.setAdapter(adapter);
        tab.setViewPager(viewpager);
        tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                start(++text);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        animatorSetsuofang = new AnimatorSet();//组合动画
        final ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(up, "scaleX", 1f, 0f, 2f, 1f);
        final ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(up, "scaleY", 1f, 0f, 2f, 1f);

        animatorSetsuofang.setDuration(200);
        animatorSetsuofang.setInterpolator(new DecelerateInterpolator());


//        final ObjectAnimator animator = ObjectAnimator.ofFloat(sifenzhiyi, "rotation", 0f, 360f)
//                .setDuration(1000);
//        animator.setRepeatCount(ObjectAnimator.INFINITE);//无限循环
//        animator.setRepeatMode(ObjectAnimator.INFINITE);//
//        animator.setInterpolator(new LinearInterpolator());


        final ObjectAnimator redScaleX = ObjectAnimator.ofFloat(down, "scaleX", 2f, 1f, 1.5f, 1f),
                redScaleY = ObjectAnimator.ofFloat(down, "scaleY", 2f, 1f, 1.5f, 1f),
                redAlpha = ObjectAnimator.ofFloat(down, "alpha", 0.5f, 0.5f, 1f, 1f),
                redTranslationY = ObjectAnimator.ofFloat(down, "translationY", -150f, 0f, 0f, 0f);
        final AnimatorSet redAnimatorSet = new AnimatorSet();
        redAnimatorSet.setDuration(1000).playTogether(redScaleX, redScaleY, redAlpha, redTranslationY);


        selectbar.setOnselecte(new SelecteUtil.Onselecte() {
            @Override
            public boolean onselected(View v, int index) {
                switch (index) {
                    case 0:
                        hahaprogress.start();
                        break;
                    case 1:
                        redAnimatorSet.start();
                        break;
                    case 2:
//                        animator.start();
                        break;
                    case 3:
                        animatorSetsuofang.play(scaleX1).with(scaleY1);//两个动画同时开始
                        animatorSetsuofang.start();
                        break;
                    case 4:
                        startReadyIn();
                        break;
                    case 5:
                        break;
                }
                return false;
            }
        });


        outToLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivRead.setVisibility(View.GONE);
                ivGo.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startReadyIn() {
        ivRead.setVisibility(View.VISIBLE);
        ivGo.setVisibility(View.VISIBLE);
        ivRead.startAnimation(inFromLeft);
        ivGo.startAnimation(inFromRight);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivRead.startAnimation(outToLeft);
                        ivGo.startAnimation(outToRight);
                    }
                });
            }
        }, 1000);
    }

    private void start(int progress) {
        up.setText("     x" + progress + "     ");
        animatorSetsuofang.start();
    }

    int text = 1000;

}
