package com.bai.nfc.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bai.nfc.R;
import com.bai.nfc.bean.GoodsList;
import com.bai.nfc.util.RequestUtil;
import com.bai.nfc.zbar.CaptureActivity;
import com.orhanobut.hawk.Hawk;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeActivity extends BaseActivity {
    @BindView(R.id.code)
    ImageView code;
    @BindView(R.id.scan)
    TextView scan;
    private static final int REQUEST_CODE_SCAN = 0x0000;// 扫描二维码
    private String payWay = "1";

    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_code);
    }

    @Override
    public void initView() {
        payWay = intentString("payWay");
    }

    @Override
    public void onSuccess(String Tag, String value) {
//        switch ()
    }

    @OnClick(R.id.scan)
    public void onViewClicked() {
        //动态权限申请
        if (ContextCompat.checkSelfPermission(CodeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CodeActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            goScan();
        }
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
                        RequestUtil.consumHoloConin(getIP(), "5", payWay, result, temp, this);
                        scan.setText("扫描结果：" + result);
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
}
