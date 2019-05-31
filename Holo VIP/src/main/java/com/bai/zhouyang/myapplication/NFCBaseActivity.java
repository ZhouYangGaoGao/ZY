package com.bai.zhouyang.myapplication;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhy.zlib.view.TopBar;

import butterknife.BindView;

public class NFCBaseActivity extends BaseActivity {

    protected NfcAdapter nfcAdapter;

    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_payed)
    TextView tvPayed;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.topbar)
    TopBar topbar;


    @Override
    public View contentView(Bundle savedInstanceState) {
        return getView(R.layout.activity_nfcbase);
    }

    @Override
    public void initView() {

    }


    public static String[][] TECHLISTS;
    @SuppressLint("NewApi")
    public static IntentFilter[] FILTERS;

    static {
        try {
            TECHLISTS = new String[][]{{IsoDep.class.getName()},
                    {NfcV.class.getName()}, {NfcF.class.getName()},};

            FILTERS = new IntentFilter[]{new IntentFilter(
                    NfcAdapter.ACTION_TECH_DISCOVERED, "*/*")};
        } catch (Exception e) {
        }
    }

}
