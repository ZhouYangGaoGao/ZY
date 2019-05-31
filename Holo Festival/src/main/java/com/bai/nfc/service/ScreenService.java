package com.bai.nfc.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ScreenService extends Service {
    public ScreenService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
