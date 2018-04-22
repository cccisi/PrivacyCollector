package com.cccisi.privacycollector.xsy;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cccisi.privacycollector.R;
import com.cccisi.privacycollector.xsy.Utils.Constant;
import com.cccisi.privacycollector.xsy.Utils.LocationUtils;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfo extends ListFragment implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = "UserInfo";
    private static final int HALF_HOUR = 1000 * 60 * 30;
    ArrayList list = new ArrayList();

    LocationUtils.OnLocationChangeListener mListener;

    public UserInfo() {

        // Required empty public constructor
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (EasyPermissions.hasPermissions(getContext(), Constant.USER_PERMISSIONS))
        {
            new UserAsyncTask().execute();
            Log.d(TAG, "onCreate: ");
        }else{
            EasyPermissions.requestPermissions(this,"read_Contacts",0,Constant.USER_PERMISSIONS);
            Log.d(TAG, "onCreate: No permissions");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        new UserAsyncTask().execute();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    class UserAsyncTask extends AsyncTask<Void,Void,ArrayList>{

        @Override
        protected ArrayList doInBackground(Void... voids) {
            getLocation();
            getContacts();
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
            if (isCancelled()) return;
            Log.d(TAG, "onPostExecute: ");
            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList));
        }
    }

    public ArrayList getLocation(){
//        if(LocationUtils.register(HALF_HOUR,100,mListener)){
//
//        };

        return list;
    }

    public ArrayList getContacts() {
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
        list.add("亲密联系人：");
        int count = 0;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                for (String contact:Constant.SELECT_CONTACT){
                    if(name.toLowerCase().contains(contact.toLowerCase())){
                        count++;
                        list.add(name+"："+number);
                    }
                }
            }
            cursor.close();
        }
        if(count == 0)
            list.add("      暂未检索到密切联系人");

        return list;
    }
}
