package com.bai.nfc.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.activity.BalanceActivity;
import com.bai.nfc.activity.CodeActivity;
import com.bai.nfc.activity.GoodsActivity;
import com.bai.nfc.activity.HomeActivity;
import com.bai.nfc.activity.LoginActivity;
import com.bai.nfc.activity.OrderActivity;
import com.bai.nfc.bean.Code;
import com.bai.nfc.bean.GoodsList;
import com.bai.nfc.util.Constant;
import com.bai.nfc.util.RequestUtil;
import com.bai.nfc.zbar.CaptureActivity;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.ClickListener;
import com.zhy.zlib.listener.CommonListener;
import com.zhy.zlib.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends ListFragment {
    List<String> datas;
    private static final int REQUEST_CODE_SCAN = 0x0000;// 扫描二维码

    @Override
    public CommonAdapter initAdapter() {
        return new CommonAdapter<String>(getContext(), datas = new ArrayList<>(), R.layout.item_menu) {
            @Override
            public void convert(final ViewHolder h, String i) {
                h.setText(R.id.tv_name, i);
                h.setClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (h.getmPosition()) {
                            case 0:
                                startActivity(new Intent(getContext(), GoodsActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(getContext(), OrderActivity.class));
                                break;
                            case 2:
                                //动态权限申请
                                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                                } else {
                                    ((HomeActivity)getActivity()).goScan();
                                }
                                break;
                            case 3:
                                DialogUtils.showDoubleBtnDialog(getActivity(), "退出登录", "您确定退出登录吗?", new ClickListener() {
                                    @Override
                                    public void yes() {
                                        Hawk.delete(Constant.MERCHAN_ID);
                                        startActivity(new Intent(getContext(), LoginActivity.class));
                                    }
                                });
                                break;
                        }
                    }
                });

            }
        };
    }

    @Override
    public void initViewData() {
        topbar.mCenterText.setText("菜单");
        datas.add("点单");
        datas.add("收支明细");
        datas.add("扫码退货");
        datas.add("退出登录");
        refresh.setEnableRefresh(false);
        refresh.setEnableLoadMore(false);
        upData();
    }

    @Override
    public void getData() {

    }

    @Override
    public boolean isGrid() {
        return true;
    }
}
