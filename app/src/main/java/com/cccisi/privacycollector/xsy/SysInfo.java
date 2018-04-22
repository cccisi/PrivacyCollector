package com.cccisi.privacycollector.xsy;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cccisi.privacycollector.R;
import com.cccisi.privacycollector.xsy.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;


//TODO 添加 运营商 手机号
public class SysInfo extends ListFragment implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = "SysInfo";
    private SysAsyncTask sysAsyncTask;
    ArrayList list = new ArrayList();

    public SysInfo() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (EasyPermissions.hasPermissions(getContext(), Constant.SYS_PERMISSIONS))
        {
            sysAsyncTask = new SysAsyncTask();
            sysAsyncTask.execute();
            Log.d(TAG, "onCreate: ");
        }else{
            EasyPermissions.requestPermissions(this,"read_phoneState",1,Constant.SYS_PERMISSIONS);
            Log.d(TAG, "onCreate: No permissions");
        }
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_sys_info, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (sysAsyncTask != null && sysAsyncTask.getStatus() == AsyncTask.Status.RUNNING)
            sysAsyncTask.cancel(true);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms){
        new SysAsyncTask().execute();
        Log.d(TAG, "onPermissionsGranted: "+perms);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms){
        Log.d(TAG, "onPermissionsDenied: "+perms);
    }

    public void getSysInfo(){
        String[] SInfo = new String[5];
        SInfo[0] = "手机品牌：" + Build.BRAND;
        SInfo[1] = "手机产品名：" + Build.PRODUCT;
        SInfo[2] = "系统版本：" + Build.VERSION.RELEASE;
        SInfo[3] = "SDK版本：" + Build.VERSION.SDK_INT;
        SInfo[4] = "OS版本：" + System.getProperty("os.version");


        for (int i = 0; i < SInfo.length; i++) {
            if (SInfo[i] != null) list.add(SInfo[i]);
        }
    }

    public void getPhoneInfo(){
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String number = telephonyManager.getLine1Number();
        if (number != null && !number.isEmpty()){
            String phoneNumber = "本机号码：" + telephonyManager.getLine1Number();
            list.add(phoneNumber);
        }

        int dataActivity = telephonyManager.getDataState();
        String state = null;
        switch (dataActivity){
            case 0:
                state = " 数据连接状态：断开或WIFI连接";
                break;
            case 1:
                state = " 数据连接状态：正在连接";
                break;
            case 2:
                state = " 数据连接状态：已连接";
                break;
            case 3:
                state= " 数据连接状态：已暂停";

        }
        if (state != null && !state.isEmpty())
            list.add(state);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            if (telephonyManager.getPhoneCount()== 1) {
                list.add("手机类型： 单卡");
            }else if (telephonyManager.getPhoneCount() == 2) {
                list.add("手机类型：双卡");
            }
        }


        Uri uri = Uri.parse("content://telephony/siminfo");
        Cursor cursor;
        int id = 0;
        ContentResolver contentResolver = getContext().getContentResolver();
        cursor = contentResolver.query(uri,new String[]{"_id","sim_id","display_name"},"0=0",new String[]{},null);
        if(null != cursor){
            while (cursor.moveToNext()){
                id = cursor.getInt(cursor.getColumnIndex("_id"));
                int sim_id = cursor.getInt(cursor.getColumnIndex("sim_id"));
                String display_name = cursor.getString(cursor.getColumnIndex("display_name"));
                //卡1
                if (sim_id == 0){
                    list.add("卡1："+display_name);
                   if (display_name.equals("中国电信")){
                       list.add("MEID："+telephonyManager.getDeviceId(sim_id));
                   }else{
                       list.add("IMEI："+telephonyManager.getDeviceId(sim_id));
                   }
                }else if(sim_id == 1){     //卡2
                    list.add("卡2："+display_name);
                    if (display_name.equals("中国电信")){
                        list.add("MEID："+telephonyManager.getDeviceId(sim_id));
                    }else{
                        list.add("IMEI："+telephonyManager.getDeviceId(sim_id));
                    }
                }
            }
            if (id == 1){
                list.add("卡2：未插卡");
            }
        }
        cursor.close();


    }
    class SysAsyncTask extends AsyncTask<Void,Void,ArrayList> {

        @Override
        protected ArrayList doInBackground(Void... voids) {
            getSysInfo();
            getPhoneInfo();
            Log.d(TAG, "doInBackground: ");
            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
            if (isCancelled()) return;
            Log.d(TAG, "onPostExecute: ");
            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList));
        }
    }


}
