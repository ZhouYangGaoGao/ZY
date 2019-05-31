package com.bai.nfc.activity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.bean.BaseBean;
import com.bai.nfc.bean.GoodsList;
import com.bai.nfc.bean.Ring;
import com.bai.nfc.fragment.GoodsFragment;
import com.bai.nfc.util.Constant;
import com.bai.nfc.util.RequestUtil;
import com.bai.nfc.util.TextStyle;
import com.bai.nfc.util.Urls;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.view.TopBar;

import java.io.IOException;
import java.util.List;

public class BalanceActivity extends NFCBaseActivity {
    private byte password[] = {(byte) 0x32, (byte) 0x45, (byte) 0x54,
            (byte) 0xf7, (byte) 0x72, (byte) 0x94, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
            (byte) 0xff, (byte) 0xff};
    private boolean isnews = true;
    private PendingIntent pendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;

    final static int NULL = -1;
    final static int BALANCE = 0;
    final static int PAY = 1;
    final static int REFUN = 2;
    int type = NULL;

    @Override
    public void initView() {
        super.initView();
    }

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
        type = getIntent().getIntExtra("type", NULL);
        if (type == NULL)
            onNewIntent(getIntent());
    }


    @Override
    protected void onResume() {
        super.onResume();
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
        log("------->", "processIntent--type==" + type);
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

                showLoading();
                RequestUtil.checkHandRing(ringId, ByteArrayToHexString(id), this);
            } else {
                Toast.makeText(this, "未识别该手环", Toast.LENGTH_SHORT).show();
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
    boolean isPaySuc;
    String ringId;

    @Override
    public void onSuccess(String Tag, String value) {
        cleanScreen();
        switch (Tag) {
            case Urls.check_HandRing:
                ring = JSON.parseObject(value, Ring.class);
                if (ring != null && ring.getCustomer() != null) {
                    tvId.setText("余额: " + ring.getCustomer().getAccountBalance() / 100d + " H");
                    try {
                        if (mIzkcService != null)
                            mIzkcService.showString(tvId.getText().toString(), 0, 150, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    switch (type) {
                        case NULL:
                        case BALANCE:
                            break;
                        case REFUN:
                            startActivity(new Intent(this, RefunListActicvity.class).putExtra(
                                    Constant.HAND_RING_ID, ringId).putExtra(Constant.COUSTOMER_ID,
                                    ring.getCustomer().getCustomerId()));
                            finish();
                            break;
                        case PAY:
                            if (!isPaySuc) {
                                amount = getIntent().getDoubleExtra("payAmount", 0);
                                if (amount > ring.getCustomer().getAccountBalance()) {
                                    topbar.mCenterText.setText("支付失败!");
                                    tvPayed.setText("余额不足!");
                                    isPaySuc = false;

                                    try {
                                        mIzkcService.showString("支付失败! 余额不足!", 0, 150, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }


                                } else if (!isOnOnPaying) {
                                    List<GoodsList.PageInfoBean.ListBean> temp = Hawk.get("selecetGoods");
                                    RequestUtil.consumHoloConin(ring.getCustomer().getCustomerId(), temp, this);
                                    isOnOnPaying = true;
                                }
                            }
                            break;
                    }
                }
                break;
            case Urls.consumHoloConin:
                topbar.mCenterText.setText("支付成功!");
                tvPayed.setText("本次消费:" + amount / 100d + " H");
                tvId.setText("余额: " + (ring.getCustomer().getAccountBalance() - amount) / 100d + " H");
                isPaySuc = true;

                GoodsFragment.needRefresh = true;
                tvBack.setVisibility(View.VISIBLE);
                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                try {
                    mIzkcService.showString("支付成功! ", 0, 50, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
                    mIzkcService.showString(tvPayed.getText().toString(), 0, 100, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
                    mIzkcService.showString(tvId.getText().toString(), 0, 150, 480, 30, 30, TextStyle.GUI_COLOR_WHITE, TextStyle.GUI_ALIGN_CENTER);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    boolean isOnOnPaying = false;

    @Override
    public void onFinish(String Tag, String value) {
        disLoading();

    }

    @Override
    public void onFailure(String Tag, String value) {
        super.onFailure(Tag, value);
        switch (Tag) {
            case Urls.check_HandRing:
                tvId.setText(value);
                break;
            case Urls.consumHoloConin://消费结果
                topbar.mCenterText.setText("支付失败!");
                tvPayed.setText("余额不足!");
                isPaySuc = false;
                break;
        }
    }

    @Override
    public void onException(String Tag, String value) {
        switch (Tag) {
            case Urls.check_HandRing:
                BaseBean baseBean= JSON.parseObject(value,BaseBean.class);
                if (baseBean.getErrorCode().equals("09")){
                    tvId.setText("手环未激活!");
                }else {
                    tvId.setText(baseBean.getErrorMsg());
                }

                break;
            case Urls.consumHoloConin://消费结果
                topbar.mCenterText.setText("支付失败!");
                tvPayed.setText("余额不足!");
                isPaySuc = false;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (mIzkcService != null)
                mIzkcService.showCleanAllScreen();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onDestroy();

    }
}
