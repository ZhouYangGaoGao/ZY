package com.zhy.zlib.Base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.zlib.R;
import com.zhy.zlib.listener.CommonListener;
import com.zhy.zlib.manager.AppManager;
import com.zhy.zlib.utils.LogUtils;

public abstract class LibActivity extends AppCompatActivity implements CommonListener {
    Dialog loading;

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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void log(String Tag, String value) {
        LogUtils.i(this.getClass().getName() + "==" + Tag, value);
    }

    @Override
    public View getView(int id) {
        return View.inflate(this, id, null);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void disLoading() {
        if (loading != null) {
            loading.dismiss();
        }
    }

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
