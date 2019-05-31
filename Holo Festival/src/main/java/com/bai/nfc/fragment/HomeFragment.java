package com.bai.nfc.fragment;

import android.content.Intent;
import android.view.View;

import com.bai.nfc.R;
import com.bai.nfc.activity.BalanceActivity;
import com.bai.nfc.activity.GatheringActivity;
import com.bai.nfc.activity.GoodsActivity;
import com.bai.nfc.activity.LoginActivity;
import com.bai.nfc.activity.OrderActivity;
import com.bai.nfc.activity.RefunListActicvity;
import com.bai.nfc.util.Constant;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.Base.ListFragment;
import com.zhy.zlib.adapter.CommonAdapter;
import com.zhy.zlib.listener.ClickListener;
import com.zhy.zlib.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends ListFragment {
    List<String> datas;

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
                                startActivity(new Intent(getContext(), BalanceActivity.class).putExtra("type", 2));
                                break;
                            case 2:
                                startActivity(new Intent(getContext(), OrderActivity.class));
                                break;
                            case 3:
                                startActivity(new Intent(getContext(), BalanceActivity.class).putExtra("type", 0));
                                break;
                            case 4:
//                                startActivity(new Intent(getContext(), GatheringActivity.class));
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
        datas.add("退货");
        datas.add("收支明细");
        datas.add("手环余额查询");
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
