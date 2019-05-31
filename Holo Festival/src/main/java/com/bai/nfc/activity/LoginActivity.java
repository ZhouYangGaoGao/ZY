package com.bai.nfc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.fragment.HomeFragment;
import com.bai.nfc.util.Constant;
import com.bai.nfc.util.RequestUtil;
import com.bai.nfc.util.Urls;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.utils.OKUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_merchantid)
    EditText etMerchantid;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.but_login)
    Button butLogin;

//    String merchantId = "18701646700";
//    String password = "123456";

    String merchantId;
    String password;

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_loglin);
    }

    @Override
    public void initView() {
        if (Hawk.get(Constant.MERCHAN_ID) != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @OnClick(R.id.but_login)
    public void onViewClicked() {

        if (loginCheck()) {
            showLoading();
            RequestUtil.login(merchantId, password, this);
        } else {
            Toast.makeText(this, "请正确输入商户名和密码", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean loginCheck() {
        merchantId = etMerchantid.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        return !TextUtils.isEmpty(merchantId) && !TextUtils.isEmpty(password);
    }

    @Override
    public void onSuccess(String Tag, String value) {
        Hawk.put(Constant.MERCHAN_ID, merchantId);
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void onFinish(String Tag, String value) {
        disLoading();
    }

    @Override
    public void onFailure(String Tag, String value) {
        Toast.makeText(this, "登录失败!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onException(String Tag, String value) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }
}
