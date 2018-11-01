package com.zhy.zlib.Base;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.zlib.R;
import com.zhy.zlib.listener.CommonListener;
import com.zhy.zlib.utils.LogUtils;
import com.zhy.zlib.utils.ScreenUtils;

public abstract class LibFragment extends Fragment implements CommonListener {

    View rootView;
    Dialog loading;
    private TextView tvLoadingMessage;
    private int loadingBgRes = R.drawable.shape_bg;
    private int loadingColor = 0xffffffff;
    private int loadingMsgColor = 0xffffffff;
    private float loadingMsgSize = 12;
    private boolean loadingOutDis = false;
    private String indicatorName = LibConfig.loading_name[0];
    private String loadingMessage = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = contentView(savedInstanceState);
        initView();
        return rootView;
    }

    @Override
    public void showLoading() {
        if (loading == null && getActivity() != null) {
            loading = new Dialog(getActivity());
            View view = getView(R.layout.loading_layout);
            tvLoadingMessage = view.findViewById(R.id.message_text);
            tvLoadingMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX,ScreenUtils.sp2px(getContext(), loadingMsgSize));
            tvLoadingMessage.setTextColor(loadingMsgColor);
            AVLoadingIndicatorView av = view.findViewById(R.id.avl);
            av.setIndicator(indicatorName);
            av.setIndicatorColor(loadingColor);
            Window window = loading.getWindow();
            window.setBackgroundDrawableResource(loadingBgRes);
            loading.setCanceledOnTouchOutside(loadingOutDis);
            loading.setContentView(view);
        }
        if (tvLoadingMessage != null) {
            if (TextUtils.isEmpty(loadingMessage)) {
                tvLoadingMessage.setVisibility(View.GONE);
            } else {
                tvLoadingMessage.setVisibility(View.VISIBLE);
                tvLoadingMessage.setText(loadingMessage);
            }
        }
        loading.show();
    }

    @Override
    public void disLoading() {
        if (loading != null) {
            loading.dismiss();
        }
    }
    public void setLoadingMsgColor(int loadingMsgColor) {
        this.loadingMsgColor = loadingMsgColor;
    }

    public void setLoadingMsgSize(float loadingMsgSize) {
        this.loadingMsgSize = loadingMsgSize;
    }

    public void setLoadingBgRes(int loadingBgRes) {
        this.loadingBgRes = loadingBgRes;
    }

    public void setLoadingColor(int loadingColor) {
        this.loadingColor = loadingColor;
    }

    public void setLoadingOutDis(boolean loadingOutDis) {
        this.loadingOutDis = loadingOutDis;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }

    @Override
    public void onSuccess(String Tag, String value) {

    }

    @Override
    public void onException(String Tag, String value) {

    }

    @Override
    public void onFailure(String Tag, String value) {

    }

    @Override
    public void onFinish(String Tag, String value) {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void log(String Tag, String value) {
        LogUtils.i(this.getClass().getName() + "==" + Tag, value);
    }

    @Override
    public View getView(int id) {
        return View.inflate(getContext(), id, null);
    }

    public View findViewById(int id) {
        if (rootView == null) {
            rootView = contentView(null);
        }
        return rootView.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disLoading();
    }
}
