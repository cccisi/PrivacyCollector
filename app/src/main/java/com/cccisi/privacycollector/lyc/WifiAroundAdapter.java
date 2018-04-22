package com.cccisi.privacycollector.lyc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cccisi.privacycollector.R;

import java.util.List;

public class WifiAroundAdapter  extends ArrayAdapter<WifiAround> {
    private int resourceId;
    public WifiAroundAdapter(Context context, int resourceId, int textViewResourceId, List<WifiAround> objects){
        super(context,resourceId,textViewResourceId,objects);
        this.resourceId=resourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        WifiAround wifi_current=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView wifiAroundName=(TextView)view.findViewById(R.id.wifiaround_name);
        TextView wifiAroundBssid=(TextView)view.findViewById(R.id.wifiaround_bssid);
        TextView wifiAroundKeymanagement=(TextView)view.findViewById(R.id.wifiaround_keymanagement);
        TextView wifiAroundLevel=(TextView)view.findViewById(R.id.wifiaround_level);
        RatingBar wifiAroundRating=(RatingBar)view.findViewById(R.id.wifiaround_rating);
        wifiAroundName.setText("网络名称："+wifi_current.getName());
        wifiAroundBssid.setText("BSSID："+wifi_current.getBssid());
        wifiAroundKeymanagement.setText("加密方式："+wifi_current.getKeymanagement());
        float i=0;
        wifiAroundLevel.setText("信号强度:");
        if(wifi_current.getLevel()<=0 & wifi_current.getLevel()>-35){
            i=4;
            wifiAroundRating.setRating(i);
        }
        else if(wifi_current.getLevel()<=-35 & wifi_current.getLevel()>-55){
            i=(float)(4.0+(wifi_current.getLevel()+35.0)/20.0);
            wifiAroundRating.setRating(i);
        }
        else if(wifi_current.getLevel()<=-55 & wifi_current.getLevel()>-75){
            i=(float)(3.0+(wifi_current.getLevel()+55.0)/20.0);
            wifiAroundRating.setRating(i);
        }
        else if(wifi_current.getLevel()<=-75 & wifi_current.getLevel()>-85){
            i=(float)(2.0+(wifi_current.getLevel()+75.0)/10.0);
            wifiAroundRating.setRating(i);
        }
        else if(wifi_current.getLevel()<=-85 & wifi_current.getLevel()>-100){
            i=(float)(1.0+(wifi_current.getLevel()+85.0)/15.0);
            wifiAroundRating.setRating(i);
        }
        else if(wifi_current.getLevel()<=-100){
//            i=(float)(1.0+(wifi_current.getLevel()+100.0)/50.0);
//            if(i<0)i=0;
            wifiAroundRating.setRating(i);
        }
        else{
            wifiAroundLevel.setText("信号强度未知");
        }
//        wifiAroundLevel.setText("信号强度："+wifi_current.getLevel());
        return view;
    }
}
