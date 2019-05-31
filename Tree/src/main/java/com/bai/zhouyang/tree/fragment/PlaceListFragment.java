package com.bai.zhouyang.tree.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bai.zhouyang.tree.R;
import com.bai.zhouyang.tree.activity.TestActivity;
import com.bai.zhouyang.tree.bean.Place;
import com.bai.zhouyang.tree.util.Constant;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.TopListener;
import com.zhy.zlib.utils.LogUtils;
import com.zhy.zlib.utils.SelecteUtil;
import com.zhy.zlib.view.SelectBar;

import java.util.ArrayList;
import java.util.List;

public class PlaceListFragment extends ListFragment {

    List<Place> datas = new ArrayList<>();

    @Override
    public CommonAdapter initAdapter() {
        return new CommonAdapter<Place>(getActivity(), datas, R.layout.item_place) {
            @Override
            public void convert(ViewHolder h, final Place i) {
                SelectBar selectBar = h.getView(R.id.sb_place);
                selectBar.initText(i.getName() + "|" + i.getHeight() + "|" + i.getWidth() + "|" + i.getUsedCount() + "|" + i.getCellTotal());
                if (h.getmPosition() % 2 == 1) {
                    selectBar.setBackgroundColor(0xff666666);
                } else {
                    selectBar.setBackgroundColor(0xff333333);
                }
                selectBar.setOnselecte(new SelecteUtil.Onselecte() {
                    @Override
                    public boolean onselected(View v, int index) {
                        Hawk.put(Constant.PLACE, i);
                        startActivity(new Intent(getActivity(), TestActivity.class));
                        return false;
                    }
                });
            }
        };
    }

    @Override
    public void initViewData() {
        topbar.mCenterText.setText("地块列表");
        topbar.mRightText.setText("新建");
        topbar.mLeftText.setText("保存SQL");
        topbar.mLeftImg1.setVisibility(View.GONE);
        topbar.mRightImg1.setVisibility(View.GONE);
        refresh.setEnableLoadMore(false);
        refresh.setEnableRefresh(false);
        topbar.setOnTopListener(new TopListener() {
            @Override
            public void rTClick() {
                creatNewPlace();
            }

            @Override
            public void lTClick() {
                for (int i = 0; i <datas.size() ; i++) {
                    datas.get(i).save();
                }
            }
        });
    }

    @Override
    public void getData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Hawk.contains(Constant.PLACE_LIST)) {
            List<Place> tmp = Hawk.get(Constant.PLACE_LIST);
            if (tmp.size() > 0) {
                datas.clear();
                datas.addAll(tmp);
                upData();
            }
        }
    }

    @Override
    public View addHearder() {
        SelectBar selectBar = new SelectBar(getActivity());
        selectBar.initText("地块名称|总行数|总列数|已载种|总植位");
        selectBar.setBackgroundColor(0xff666666);
        return selectBar;
    }

    Dialog dialog;

    private void creatNewPlace() {
        if (dialog == null) {
            View view = View.inflate(getActivity(), R.layout.dialog_create, null);
            dialog = new Dialog(getActivity());
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            final EditText name = view.findViewById(R.id.edt_name);
            final EditText width = view.findViewById(R.id.edt_width);
            final EditText height = view.findViewById(R.id.edt_height);
            view.findViewById(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strN = name.getText().toString();
                    String strH = height.getText().toString();
                    String strW = width.getText().toString();
                    if (TextUtils.isEmpty(strH) || TextUtils.isEmpty(strN) || TextUtils.isEmpty(strW)) {
                        Toast.makeText(getActivity(), "请输入正确信息", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    datas.add(new Place(strN, Integer.parseInt(strH), Integer.parseInt(strW)));
                    upData();
                    Hawk.put(Constant.PLACE_LIST, datas);
                    dialog.dismiss();
                }
            });
            view.findViewById(R.id.tv_no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.addContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    dialog = null;
                }
            });
        }
        dialog.show();
    }
}
