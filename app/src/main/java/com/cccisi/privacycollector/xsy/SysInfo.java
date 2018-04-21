package com.cccisi.privacycollector.xsy;


import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cccisi.privacycollector.R;

import java.util.ArrayList;


//TODO 添加 运营商 手机号

public class SysInfo extends ListFragment {
    private static final String TAG = "SysInfo";
    private static final String[] PERMISSIONS = {Manifest.permission.READ_PHONE_STATE};
    ArrayList list = new ArrayList();

    public SysInfo() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        getSysInfo();
        getPhoneInfo(getContext());
        this.setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_sys_info, container, false);
    }

    public void getSysInfo(){
        String[] SInfo= new String[5];
        SInfo[0] = "手机品牌：" + Build.BRAND;
        SInfo[1] = "手机产品名："+Build.PRODUCT;
        SInfo[2] = "系统版本："+Build.VERSION.RELEASE;
        SInfo[3] = "SDK版本："+Build.VERSION.SDK_INT;
        SInfo[4] = "OS版本："+System.getProperty("os.version");

        for (int i = 0;i < SInfo.length;i++){
            if (SInfo[i] != null)
                list.add(SInfo[i]);
        }
    }


    public void getPhoneInfo(Context context){
        if (!EasyPermissions.hasPermissions(context,PERMISSIONS))
        {
            return;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = "本机号码："+telephonyManager.getLine1Number();
        list.add(phoneNumber);
    }


}
