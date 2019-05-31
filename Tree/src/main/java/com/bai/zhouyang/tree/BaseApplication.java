package com.bai.zhouyang.tree;

import com.orhanobut.hawk.Hawk;
import com.orm.SugarContext;
import com.zhy.zlib.Base.LibApplication;

public class BaseApplication extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

}
