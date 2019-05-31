package com.example.anim;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.anim.activity.BaseActivity;
import com.example.anim.fragment.FourFragment;
import com.example.anim.fragment.OneFragment;
import com.example.anim.fragment.ThreeFragment;
import com.example.anim.fragment.TwoFragment;
import com.example.anim.view.MaterialMenuDrawable;
import com.example.anim.view.MaterialMenuView;
import com.zhy.zlib.utils.ScreenUtils;
import com.zhy.zlib.utils.SelecteUtil;
import com.zhy.zlib.view.SelectBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.bg)
    View bg;
    @BindView(R.id.my_viewPager)
    ViewPager myViewPager;
    @BindView(R.id.selectBar)
    SelectBar selectBar;
    @BindView(R.id.material_menu_button)
    MaterialMenuView materialMenuButton;

    SelecteUtil slu;

    float bgWith = 0;


    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        bgWith = ScreenUtils.getScreenWidth(this);
        selectBar.setOnselecte(new SelecteUtil.Onselecte() {
            @Override
            public boolean onselected(View v, int index) {
                changeMenu(index);
                return false;
            }
        });
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                changeMenu(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        slu = new SelecteUtil().withViewPager(myViewPager, getSupportFragmentManager(), new OneFragment(), new TwoFragment(), new ThreeFragment(), new FourFragment());
        bg.setPivotX(0);
        ObjectAnimator.ofFloat(bg, "scaleX", 0.25f, 0.25f).setDuration(1).start();
    }

    int now = 0;

    private void changeMenu(int i) {
        ObjectAnimator.ofFloat(bg, "scaleX", (now + 1) / 4f, (i + 1) / 4f).start();
        now = i;
        switch (i) {
            case 0:
                materialMenuButton.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                break;
            case 1:
                materialMenuButton.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                break;
            case 2:
                materialMenuButton.animateIconState(MaterialMenuDrawable.IconState.X);
                break;
            case 3:
                materialMenuButton.animateIconState(MaterialMenuDrawable.IconState.CHECK);
                break;

        }
    }

}
