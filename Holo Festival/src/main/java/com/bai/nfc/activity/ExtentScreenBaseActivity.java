package com.bai.nfc.activity;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.bai.nfc.bean.GoodsList;
import com.bai.nfc.util.TextStyle;
import com.smartdevice.aidl.IZKCService;

public abstract class ExtentScreenBaseActivity extends BaseActivity {


    @Override
    public void initView() {
        module_flag = getIntent().getIntExtra(MODULE_FLAG, 8);
        bindService();
    }

    /**
     * ------------------------------------------------------------------------------------------------
     */
    public static String MODULE_FLAG = "module_flag";
    public static int module_flag = 0;
    public static int DEVICE_MODEL = 0;
    public static IZKCService mIzkcService;
    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("client", "onServiceDisconnected");
            mIzkcService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("client", "onServiceConnected");
            mIzkcService = IZKCService.Stub.asInterface(service);
            if (mIzkcService != null) {
                try {
                    //获取产品型号 get product model
                    DEVICE_MODEL = mIzkcService.getDeviceModel();
                    //设置当前模块 set current function module
                    mIzkcService.setModuleFlag(module_flag);
                    ExtentScreenBaseActivity.this.onServiceConnected();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void bindService() {
        //com.zkc.aidl.all为远程服务的名称，不可更改
        //com.smartdevice.aidl为远程服务声明所在的包名，不可更改，
        // 对应的项目所导入的AIDL文件也应该在该包名下
        Intent intent = new Intent("com.zkc.aidl.all");
        intent.setPackage("com.smartdevice.aidl");
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    public void unbindService() {
        unbindService(mServiceConn);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        unbindService();
        super.onDestroy();
    }

    public void onServiceConnected() {

    }

    public void cleanScreen() {
        try {
            if (mIzkcService != null)
                mIzkcService.showCleanAllScreen();
//            mIzkcService.showFillColor(0, 0, 480, 320, "#000000");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
