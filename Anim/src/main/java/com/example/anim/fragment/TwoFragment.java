package com.example.anim.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.example.anim.R;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends ListFragment {

    List<String> datas = new ArrayList<>();

    @Override
    public CommonAdapter initAdapter() {
        return new CommonAdapter<String>(getContext(), datas, R.layout.item_one) {
            @Override
            public void convert(final ViewHolder h, String i) {
                final AnimatorSet redAnimatorSet = new AnimatorSet();
                redAnimatorSet.setDuration(100).playTogether(ObjectAnimator.ofFloat(h.getConvertView(),
                        "scaleX", 1f, 0.7f, 1f), ObjectAnimator.ofFloat(h.getConvertView(), "scaleY", 1f, 0.7f, 1f));
                h.setText(R.id.name, "ARROW" + i);
                h.setClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        redAnimatorSet.start();
                    }
                });
            }
        };
    }

    @Override
    public void initViewData() {
        datas.add("11111");
        datas.add("22222");
        datas.add("33333");
        datas.add("44444");
        datas.add("55555");
        datas.add("66666");
        datas.add("77777");
        datas.add("88888");
        upData();
    }

    @Override
    public void getData() {

    }
}
