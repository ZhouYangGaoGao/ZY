package com.bai.nfc.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bai.nfc.R;
import com.bai.nfc.bean.Code;
import com.bai.nfc.bean.GoodsList;
import com.bai.nfc.fragment.GoodsFragment;
import com.bai.nfc.util.CodeUtil;
import com.bai.nfc.util.RequestUtil;
import com.bai.nfc.util.Urls;
import com.bai.nfc.zbar.CaptureActivity;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.listener.CommonListener;
import com.zhy.zlib.listener.TopListener;
import com.zhy.zlib.view.TopBar;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class CodeActivity extends ExtentScreenBaseActivity {
    @BindView(R.id.code)
    ImageView mCode;
    @BindView(R.id.scan)
    TextView scan;
    @BindView(R.id.topBar)
    TopBar topBar;
    private static final int REQUEST_CODE_SCAN = 0x0000;// 扫描二维码
    private int payWay = 1;
    private int payType = 4;
    private boolean isGetPayMent = false;

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_code);
    }

    @Override
    public void initView() {
        payWay = intentInt("payWay");
        //获取二维码
        List<GoodsList.PageInfoBean.ListBean> temp = Hawk.get("selecetGoods");
        RequestUtil.consumHoloConin(getIP(), payType = 4, payWay, null, temp, CodeActivity.this);
    }

    @Override
    public void onSuccess(String Tag, String value) {
        switch (Tag) {
            case Urls.consumHoloConin:
                final Code code = JSON.parseObject(value, Code.class);
                if (code != null && code.getResult() != null) {
                    if (code.getResult().getData() != null) {
                        Bitmap qrImage = CodeUtil.createQRImage(code.getResult().getData(), 260, 260);
                        topBar.mRightText.setText("等待支付中");
                        topBar.mRightText.setEnabled(false);
                        mCode.setImageBitmap(qrImage);
                        if (mIzkcService != null)
                            try {
                                mIzkcService.showQrCode(code.getResult().getData(), 260, 110, 5);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        if (timer == null) {
                            timer = new Timer();
                        }
                        timerTask = new TimerTask() {

                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RequestUtil.getTecharge(code.getOutTradeNo(), CodeActivity.this);
                                        if (isGetPayMent && !TextUtils.isEmpty(no)) {
                                            getPayment();
                                        }
                                    }
                                });
                            }
                        };
                        timer.schedule(timerTask, 5000, 5000);
                    } else if (code.getResult().getCode() == 400) {
                        if (!isGetPayMent) {
                            topBar.mRightText.setText("支付成功");
                            showToast("支付成功");
                            GoodsFragment.needRefresh = true;
                            finish();
                        }
                    } else {
                        showToast(code.getResult().getMessage());
                    }
                }
                break;
            case Urls.getTecharge:
                JSONObject jsonObject = JSONObject.parseObject(value);
                if ("1".equals(jsonObject.getString("code"))) {
                    showToast("支付成功");
                    GoodsFragment.needRefresh = true;
                    finish();
                }
                break;
            case Urls.getPayMent:
                log("getPayMent",value);
                JSONObject obj = JSONObject.parseObject(value);
                if ("SUCCESS".equals(obj.getString("data"))){
                    showToast("支付成功");
                    GoodsFragment.needRefresh = true;
                    finish();
                }
                break;
        }
    }

    private String no;

    private void getPayment() {
        RequestUtil.getPayMent(no, CodeActivity.this);
    }

    @Override
    protected void onDestroy() {
        cleanScreen();
        cancelTimer();
        super.onDestroy();
    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan() {
        Intent intent = new Intent(CodeActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:// 二维码
                // 扫描二维码回传
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_STRING);
                        List<GoodsList.PageInfoBean.ListBean> temp = Hawk.get("selecetGoods");
                        isGetPayMent = (payWay == 1);
                        RequestUtil.consumHoloConin(getIP(), payType = 5, payWay, result, temp, isGetPayMent ? new CommonListener() {
                            @Override
                            public void onSuccess(String Tag, String value) {
                                final Code code = JSON.parseObject(value, Code.class);
                                no = code.getOutTradeNo();
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
                            public View contentView(Bundle savedInstanceState) {
                                return null;
                            }

                            @Override
                            public void initView() {

                            }

                            @Override
                            public void showToast(String message) {

                            }

                            @Override
                            public void log(String Tag, String value) {

                            }

                            @Override
                            public View getView(int id) {
                                return null;
                            }

                            @Override
                            public void showLoading() {

                            }

                            @Override
                            public void disLoading() {

                            }
                        } : this);
                        scan.setText("扫描成功");

                    }
                }
                break;
            default:
                break;
        }
    }

    public static String getIP() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @OnClick({R.id.code, R.id.scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.code:

                break;
            case R.id.scan:
                //动态权限申请
                if (ContextCompat.checkSelfPermission(CodeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CodeActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
                break;
        }
    }

    private Timer timer;
    private TimerTask timerTask;

    public void cancelTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}
