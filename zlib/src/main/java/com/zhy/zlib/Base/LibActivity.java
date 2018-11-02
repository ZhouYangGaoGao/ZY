package com.zhy.zlib.Base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.zlib.R;
import com.zhy.zlib.listener.CommonListener;
import com.zhy.zlib.manager.AppManager;
import com.zhy.zlib.utils.LogUtils;
import com.zhy.zlib.utils.ScreenUtils;

public abstract class LibActivity extends AppCompatActivity implements CommonListener {
    /**
     * 加载框
     */
    Dialog loading;

    /**
     * 加载框中的文字
     */
    private TextView tvLoadingMessage;

    /**
     * 加载框北京颜色资源 id
     */
    private int loadingBgRes = R.drawable.shape_bg;

    /**
     * 加载动画显示的颜色
     */
    private int loadingColor = 0xffffffff;

    /**
     * 加载框中文字的颜色 色值
     */
    private int loadingMsgColor = 0xffffffff;

    /**
     * 加载框中文字的字体大小
     */
    private float loadingMsgSize = 12;

    /**
     * 是否可以点击外界消失加载框
     */
    private boolean loadingOutDis = false;

    /**
     * 加载动画样式
     */
    private String indicatorName = LibConfig.loading_name[0];

    /**
     * 显示的文字
     */
    private String loadingMessage = "";


    /**
     * 显示加载框
     */
    @Override
    public void showLoading() {
        if (loading == null) {
            loading = new Dialog(this);
            View view = getView(R.layout.loading_layout);
            tvLoadingMessage = view.findViewById(R.id.message_text);
            tvLoadingMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, ScreenUtils.sp2px(this, loadingMsgSize));
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

    /**
     * 隐藏加载框
     */
    @Override
    public void disLoading() {
        if (loading != null) {
            loading.dismiss();
        }
    }

    /**
     * 设置加载文字颜色
     *
     * @param loadingMsgColor
     */
    public void setLoadingMsgColor(int loadingMsgColor) {
        this.loadingMsgColor = loadingMsgColor;
    }

    /**
     * 设置加载文字大小
     *
     * @param loadingMsgSize
     */
    public void setLoadingMsgSize(float loadingMsgSize) {
        this.loadingMsgSize = loadingMsgSize;
    }

    /**
     * 设置加载框背景资源 id
     *
     * @param loadingBgRes
     */
    public void setLoadingBgRes(int loadingBgRes) {
        this.loadingBgRes = loadingBgRes;
    }

    /**
     * 设置加载动画的颜色
     *
     * @param loadingColor
     */
    public void setLoadingColor(int loadingColor) {
        this.loadingColor = loadingColor;
    }

    /**
     * 设置能否点击外部消失
     *
     * @param loadingOutDis
     */
    public void setLoadingOutDis(boolean loadingOutDis) {
        this.loadingOutDis = loadingOutDis;
    }

    /**
     * 设置加载动画样式名称
     *
     * @param indicatorName
     */
    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    /**
     * 设置加载文字
     *
     * @param loadingMessage
     */
    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }

    /**
     * 请求成功返回结果
     *
     * @param Tag
     * @param value
     */
    @Override
    public void onSuccess(String Tag, String value) {

    }

    /**
     * 请求异常返回结果
     *
     * @param Tag
     * @param value
     */
    @Override
    public void onException(String Tag, String value) {

    }

    /**
     * 请求失败返回结果
     *
     * @param Tag
     * @param value
     */
    @Override
    public void onFailure(String Tag, String value) {

    }

    /**
     * 请求结束返回结果
     *
     * @param Tag
     * @param value
     */
    @Override
    public void onFinish(String Tag, String value) {

    }

    /**
     * 显示吐司
     *
     * @param message
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打印日志
     *
     * @param Tag
     * @param value
     */
    @Override
    public void log(String Tag, String value) {
        LogUtils.i(this.getClass().getName() + "==" + Tag, value);
    }

    /**
     * 根据 layout id 获取对应View
     *
     * @param id
     * @return
     */
    @Override
    public View getView(int id) {
        return View.inflate(this, id, null);
    }

    /**
     * 销毁时销毁加载框 并将 Activity 从堆栈中移除
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
        disLoading();
    }

    /**
     * 对context进行初始化，并将当前的Activity加入到堆栈中，便于管理
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
    }

}
