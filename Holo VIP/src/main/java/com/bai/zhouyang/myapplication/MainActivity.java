package com.bai.zhouyang.myapplication;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.zlib.listener.CommonListener;
import com.zhy.zlib.listener.TopListener;
import com.zhy.zlib.utils.OKUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends NFCBaseActivity {

    private byte password[] = {(byte) 0x32, (byte) 0x45, (byte) 0x54,
            (byte) 0xf7, (byte) 0x72, (byte) 0x94, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
            (byte) 0xff, (byte) 0xff};
    private boolean isnews = true;
    private PendingIntent pendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        ndef.addCategory("*/*");
        mFilters = new IntentFilter[]{ndef};
        mTechLists = new String[][]{
                new String[]{MifareClassic.class.getName()},
                new String[]{NfcA.class.getName()}};
    }


    @Override
    protected void onResume() {
        super.onResume();
        log("nfcAdapter", (nfcAdapter = null) + "");
        log("pendingIntent", (pendingIntent = null) + "");
        log("mFilters", (mFilters = null) + "");
        log("mTechLists", (mTechLists = null) + "");
        if (nfcAdapter != null)
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, mFilters,
                    mTechLists);
        if (isnews) {
            if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent()
                    .getAction())) {
                processIntent(getIntent());
                isnews = false;
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
        processIntent(intent);
//        }

    }

    protected String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F"};
        String out = "";
        for (j = inarray.length - 1; j >= 0; j--) {
            in = inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }


    private void processIntent(Intent intent) {
        boolean auth = false;
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        MifareClassic mfc = MifareClassic.get(tag);
        byte[] id = tag.getId();

        try {
            mfc.connect();
            auth = mfc.authenticateSectorWithKeyA(0, password);
            if (auth) {
                byte[] data = mfc.readBlock(1);
                String content = bytesToHexString(data);
                ringId = content.substring(content.length() - 13, content.length());

//                showLoading();
//                checkHandRing(ringId, ByteArrayToHexString(id), this);


                tvId.setText("验证通过");
                tvId.setBackgroundResource(R.drawable.shape_dot_green);
                restTime = 5;
            } else {
                restTime = -1;
                Toast.makeText(this, "无该手环记录", Toast.LENGTH_SHORT).show();
                tvId.setText("无该手环记录");
                tvId.setBackgroundResource(R.drawable.shape_dot_red);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (mfc != null) {
                    mfc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    double amount;
    Ring ring;
    String ringId;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (restTime == 0) {
                tvId.setBackgroundResource(R.drawable.shape_dot_blue);
                tvId.setText("将手环靠近感应区");
            } else
                tvId.setText("验证通过(" + restTime-- + ")");
        }

    };

    @Override
    public void initView() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (restTime >= 0) {
                    handler.sendEmptyMessage(4);
                }
            }
        }, 0, 1000);

        topbar.setOnTopListener(new TopListener() {
            @Override
            public void lTClick() {
                tvId.setText("验证通过");
                tvId.setBackgroundResource(R.drawable.shape_dot_green);
                restTime = 5;
            }

            @Override
            public void rTClick() {
                checkHandRing("2334", "24524", MainActivity.this);
            }
        });
    }

    int restTime = 0;

    @Override
    public void onSuccess(String Tag, String value) {
        ring = JSON.parseObject(value, Ring.class);
        if (ring != null && ring.getCustomer() != null) {
//            tvId.setText("余额: " + ring.getCustomer().getAccountBalance() / 100d + " H");
            tvId.setText("验证通过(5)");
            tvId.setBackgroundResource(R.drawable.shape_dot_green);
            restTime = 5;
        }
    }

    @Override
    public void onFinish(String Tag, String value) {
        disLoading();
    }

    @Override
    public void onFailure(String Tag, String value) {
//        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onException(String Tag, String value) {
        restTime = -1;
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        tvId.setText("无该手环记录");
        tvId.setBackgroundResource(R.drawable.shape_dot_red);
    }

    /**
     * 手环检验
     *
     * @param handRingId   手环 id
     * @param handRingCode 手环密码
     * @param listener     回调
     */
    public static void checkHandRing(String handRingId,
                                     String handRingCode, CommonListener listener) {
        Map map = new HashMap();
        map.put("handRingId", handRingId);
        map.put("handRingCode", handRingCode);
        OKUtils.postJson("https://www.holofest.cn:8443/checkHandRing.do", "checkHandRing", JSON.toJSONString(map), listener);
    }
}
