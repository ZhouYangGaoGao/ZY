package com.bai.nfc.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.R;
import com.bai.nfc.bean.Order;
import com.bai.nfc.util.Constant;
import com.bai.nfc.util.RequestUtil;
import com.bai.nfc.zbar.CaptureActivity;
import com.zhy.zlib.utils.DateUtil;

public class HomeActivity extends BaseActivity {
    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_home);
    }

    @Override
    public void initView() {

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
            case 0x0000:// 二维码
                // 扫描二维码回传
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_STRING);

                        RequestUtil.queryOrderByMIdAndHId(result,this);


                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(String Tag, String value) {
        Order order= JSON.parseObject(value,Order.class);
        startActivity(new Intent(this, RingOrderDscActivity.class)
                .putExtra(Constant.CONSUMPTION_ID, order.getConsumption().getConsumptionId())
//                                .putExtra("type", "dsc")
                .putExtra(Constant.COUSTOMER_ID, getIntent().getStringExtra(Constant.COUSTOMER_ID))
                .putExtra("time", DateUtil.milliString2String(order.getConsumption().getConsumptionTime(), DateUtil.MINUTE_PATTERN)));
    }

    /**
     * 跳转到扫码界面扫码
     */
    public void goScan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 0x0000);
    }
}
