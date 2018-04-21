package com.cccisi.privacycollector.lyc;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cccisi.privacycollector.R;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cccis on 2018/4/12.
 * LYC负责
 */

public class LycActivity extends AppCompatActivity {
    private List<Wifi> wifiList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyc);
        Log.i("WifiConfig", "\n--------39-------\n");
        //获取WIFI信息
        WifiManager wifi = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        List<WifiConfiguration> configs =wifi.getConfiguredNetworks();
//        Wifi[] data=new Wifi[configs.size()];
        final Wifi[] data = new Wifi[configs.size()];
//        final Wifi[] data;
        final String[] data_name=new String[configs.size()];
        final String[] data_status=new String[configs.size()];
        final String[] data_keymanagement=new String[configs.size()];
        for(int i=0;i<configs.size();i++){
            data[i]=new Wifi("1","2","3");
        }
        int i=0;
        Log.i("WifiConfig", "\n--------50-------\n");
        for (WifiConfiguration config : configs) {
//            Log.i("WifiConfig", "---------------");
//            Log.i("WifiConfig", config.toString());
            Log.i("WifiConfig", "\n--------54-------\n");
            data[i].name=config.SSID.substring(1,config.SSID.length()-1);

            Log.i("WifiConfig", "\n--------56-------\n");
            switch (config.status){
                case 0:
                    data[i].status="当前连接";
                    break;
                case 1:
                    data[i].status="禁用";
                    break;
                case 2:
                    data[i].status="可用";
                    break;
            }

//            allowedKeyManagement-加密方式：0-NONE。没有使用WPA; 可以使用明文或静态WEP。
//            1-WPA_PSK。WPA预共享密钥（需要指定preSharedKey）。
//            2-WPA_EAP。使用EAP认证的WPA。通常与外部认证服务器一起使用。
//            3-IEEE8021X。使用EAP认证和（可选）动态生成的WEP密钥
//            public static final int IEEE8021X = 3;
//            public static final int NONE = 0;
//            public static final int WPA_EAP = 2;
//            public static final int WPA_PSK = 1;
            Log.i("WifiConfig", "\n--------74-------\n");
            if(config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.NONE) & (config.wepKeys[0]!=null)){
                data[i].keymanagement="WEP";
            }
            else if(config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.NONE)){
                data[i].keymanagement="无";
            }
            else if(config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_PSK)){
                data[i].keymanagement="WPA-PSK/WPA2-PSK";
            }
            else if(config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_EAP)){
                data[i].keymanagement="EAP";
            }
            else if(config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.IEEE8021X)){
                data[i].keymanagement="802.1x EAP";
            }
            else data[i].keymanagement="你好";

            i++;
        }

//        Log.d("data","onCreate execute");
        Log.i("WifiConfig", "\n--------97-------\n");
        Button button = (Button) findViewById(R.id.buttonlyc);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //监听逻辑

                //初始化wifi数据
                initWifi();
                WifiAdapter adapter=new WifiAdapter(
                        LycActivity.this,R.layout.wifi_item,R.id.wifi_name,wifiList);
                //创建ArrayAdapter
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                        MainActivity.this, android.R.layout.simple_list_item_1,data_name);
                //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter
                ListView listView=(ListView)findViewById(R.id.list_view);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Wifi wifi=wifiList.get(position);
                        switch (wifi.getStatus()){
                            case "当前连接":
                                Toast.makeText(LycActivity.this,"当前连接",Toast.LENGTH_SHORT).show();
                                break;
                            case "禁用":
                                Toast.makeText(LycActivity.this,"禁用",Toast.LENGTH_SHORT).show();
                                break;
                            case "可用":
                                Toast.makeText(LycActivity.this,"可用",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });

            }
            private void initWifi(){
                for (int i=0;i<data.length;i++) {
                    Wifi lyc = new Wifi(data[i].name,data[i].status,data[i].keymanagement);
                    wifiList.add(lyc);
                }
            }
        });
    }
}



