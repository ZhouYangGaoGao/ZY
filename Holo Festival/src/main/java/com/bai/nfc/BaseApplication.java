package com.bai.nfc;

import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.Base.LibApplication;

public class BaseApplication extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
