package com.cccisi.privacycollector.lyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cccisi.privacycollector.R;

import java.util.List;

public class WifiAdapter extends ArrayAdapter<Wifi> {
    private int resourceId;
    public WifiAdapter(Context context, int resourceId, int textViewResourceId, List<Wifi> objects){
        super(context,resourceId,textViewResourceId,objects);
        this.resourceId=resourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Wifi wifi_current=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView wifiName=(TextView)view.findViewById(R.id.wifi_name);
        TextView wifiStatus=(TextView)view.findViewById(R.id.wifi_status);
        TextView wifiKeymanagement=(TextView)view.findViewById(R.id.wifi_keymanagement);
        wifiName.setText("网络名称："+wifi_current.getName());
        wifiStatus.setText("网络状态："+wifi_current.getStatus());
        wifiKeymanagement.setText("加密方式："+wifi_current.getKeymanagement());
        return view;
    }
}