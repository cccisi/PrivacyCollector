package com.cccisi.privacycollector.lyc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Bundle;

import com.cccisi.privacycollector.R;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.AlertDialogLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cccis on 2018/4/12.
 * LYC负责
 */

public class LycActivity extends AppCompatActivity {
    private List<Wifi> wifiList=new ArrayList<>();
    private List<WifiAround> wifiAroundList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyc);

        Log.i("WifiConfig", "\n--------39-------\n");
//        Log.d("data","onCreate execute");
        Log.i("WifiConfig", "\n--------97-------\n");
        Button button_wifi_saved = (Button) findViewById(R.id.button_wifi_saved);
        button_wifi_saved.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //监听逻辑
            /*********************获取已保存WIFI信息*********************/
                WifiManager wifi_manager = (WifiManager)LycActivity.this.getSystemService(Context.WIFI_SERVICE);
                final WifiInfo wifi_info=wifi_manager.getConnectionInfo();

                //wifi处于关闭状态，需要先开wifi再查询，否则会返回上一级页面
                AlertDialog.Builder builder=new AlertDialog.Builder(LycActivity.this);
                builder.setTitle("提示");
                builder.setMessage("Wifi开启即可查询。正在开启中，请稍等^^");
                builder.setCancelable(true);
                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                if (!wifi_manager.isWifiEnabled()){
                    builder.show();
//                    Toast.makeText(LycActivity.this,"开启Wifi即可查询。正在开启中……",Toast.LENGTH_SHORT).show();
                    wifi_manager.setWifiEnabled(true);
                }
                while (!wifi_manager.isWifiEnabled()){
//                    builder.show();
                }

                List<WifiConfiguration> configs_wifi_saved =wifi_manager.getConfiguredNetworks();
                final Wifi[] data = new Wifi[configs_wifi_saved.size()];
                for(int i=0;i<configs_wifi_saved.size();i++){
                    data[i]=new Wifi("1","2","3",false,-1);
                }
                int i=0,j=0;
                Log.i("WifiConfig", "\n--------50-------\n");
                for (WifiConfiguration config : configs_wifi_saved) {
//            Log.i("WifiConfig", "---------------");
//            Log.i("WifiConfig", config.toString());
//                    Log.i("WifiConfig", "\n--------54-------\n");
                    //获取wifi名称
                    data[i].name=config.SSID.substring(1,config.SSID.length()-1);
                    //获取wifi状态
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
                    //获取wifi加密方式
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
                    //获取wifi是否隐藏SSID
                    data[i].hiddenSSID=config.hiddenSSID;
                    //获取wifi的连接号
                    data[i].networkid=config.networkId;
                    //将当前连接的wifi放在列表前面显示
                    if(config.status==0){
                        Wifi data_temp = new Wifi("1","2","3",false,-1);
                        data_temp=data[j];
                        data[j]=data[i];
                        data[i]=data_temp;
                        j++;
                    }
                    i++;
                }
                /*********************获取已保存WIFI信息完毕*********************/

                //初始化wifi数据

                WifiAdapter adapter=new WifiAdapter(
                        LycActivity.this,R.layout.wifi_item,R.id.wifi_name,wifiList);
                //创建ArrayAdapter
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                        MainActivity.this, android.R.layout.simple_list_item_1,data_name);
                //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter
                adapter.clear();
                initWifi(data);
                ListView listView=(ListView)findViewById(R.id.list_view_wifi_saved);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Wifi wifi=wifiList.get(position);
                        switch (wifi.getStatus()){
                            case "当前连接":
                                Toast.makeText(LycActivity.this,
                                        "BSSID："+wifi_info.getBSSID()+"\n"+
                                        "MAC地址："+wifi_info.getMacAddress()+"\n"+
                                        "IP地址："+(wifi_info.getIpAddress() & 0xff)+"."+
                                                (wifi_info.getIpAddress()>>8 & 0xff)+"."+
                                                (wifi_info.getIpAddress()>>16 & 0xff)+"."+
                                                (wifi_info.getIpAddress()>>24 & 0xff),Toast.LENGTH_SHORT).show();
                                break;
                            case "禁用":
                                if(!wifi.hiddenSSID){
                                    Toast.makeText(LycActivity.this,
                                            "networkId："+wifi.networkid+"\n"+
                                                    "是否为隐藏wifi（未广播）：否",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(LycActivity.this,
                                            "networkId："+wifi.networkid+"\n"+
                                                    "是否为隐藏wifi（未广播）：是",Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case "可用":
                                if(!wifi.hiddenSSID){
                                    Toast.makeText(LycActivity.this,
                                            "networkId："+wifi.networkid+"\n"+
                                                    "是否为隐藏wifi（未广播）：否",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(LycActivity.this,
                                            "networkId："+wifi.networkid+"\n"+
                                                    "是否为隐藏wifi（未广播）：是",Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                    }
                });

            }
            private void initWifi(Wifi[] data){
                for (int i=0;i<data.length;i++) {
                    Wifi lyc = new Wifi(data[i].name,data[i].status,data[i].keymanagement,data[i].hiddenSSID,data[i].networkid);
                    wifiList.add(lyc);
                }

            }
        });

        Button button_wifi_around = (Button) findViewById(R.id.button_wifi_around);
        final Button button_wifi_close = (Button) findViewById(R.id.button_wifi_close);
        button_wifi_close.setVisibility(View.GONE);
        button_wifi_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeWifiAp(LycActivity.this);
                button_wifi_close.setVisibility(View.GONE);
            }
        });
        button_wifi_around.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //监听逻辑

                /*********************获取周围WIFI信息*********************/
                WifiManager wifi_manager_around=(WifiManager)LycActivity.this.getSystemService(Context.WIFI_SERVICE);
                Log.i("WifiConfig", "\n--------周围wifi看一看-------\n");
                //wifi关闭时不能搜索周围wifi，需要先开启wifi
                AlertDialog.Builder builder=new AlertDialog.Builder(LycActivity.this);
                builder.setTitle("提示");
                builder.setMessage("Wifi开启即可查询。正在开启中,请稍后再次点击^^");
                builder.setCancelable(true);
                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                if(!wifi_manager_around.isWifiEnabled()){
                    builder.show();
                    wifi_manager_around.setWifiEnabled(true);
                }
                List<ScanResult> configs_wifi_around =wifi_manager_around.getScanResults();

//        sortByLevel(configs_wifi_around);
                final WifiAround[] data_around = new WifiAround[configs_wifi_around.size()];
                final WifiAround[] data_around_temp = new WifiAround[configs_wifi_around.size()];
                for(int k=0;k<configs_wifi_around.size();k++){
                    data_around[k]=new WifiAround("1","2","3",1);
                    data_around_temp[k]=new WifiAround("1","2","3",1);
                }
                int i=0,j=0;
                for (ScanResult config : configs_wifi_around) {
                    data_around_temp[i].name=config.SSID;
                    data_around_temp[i].bssid=config.BSSID;
                    data_around_temp[i].keymanagement=config.capabilities;
                    data_around_temp[i].level=config.level;
                    i++;
                }
                //将搜索到的wifi根据信号强度从强到弱进行排序
                Arrays.sort(data_around_temp, new Comparator<WifiAround>() {
                    @Override
                    public int compare(WifiAround t1, WifiAround t2) {
                        return t2.level-t1.level;
                    }
                });
                //优先显示未加密的wifi
                int temp=0;
                for(int k=0;k<configs_wifi_around.size();k++){
                    if(data_around_temp[k].keymanagement=="[ESS]"){
                        data_around[temp++]=data_around_temp[k];
                        Log.i("WifiConfig", data_around_temp[k].name);
                    }
                }
                for(int k=0;k<configs_wifi_around.size();k++) {
                    if (data_around_temp[k].keymanagement != "[ESS]") {
                        data_around[temp++] = data_around_temp[k];
                    }
                }
                /*********************获取周围WIFI信息完毕*********************/

                Log.i("WifiConfig", "\n--------周围wifi瞧一瞧-------\n");
                //创建ArrayAdapter
                WifiAroundAdapter adapter=new WifiAroundAdapter(
                        LycActivity.this,R.layout.wifiaround_item,R.id.wifiaround_name,wifiAroundList);
                //清空Listview内容
                adapter.clear();
                //初始化wifiAround数据
                initWifiAround(data_around);
                Log.i("WifiConfig", "\n--------周围wifi走一走-------\n");
                //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter
                ListView listView_around=(ListView)findViewById(R.id.list_view_wifi_saved);
                listView_around.setAdapter(adapter);
                Log.i("WifiConfig", "\n--------周围wifi转一转-------\n");
                listView_around.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.i("Open", "-------创建提示框之前--------");

                        final WifiAround wifiaround=wifiAroundList.get(position);
//                        Log.i("Open", wifiaround.keymanagement);
//                        Log.i("Open", "[ESS]");

                        if(wifiaround.keymanagement.equals("[ESS]")){
                            Log.i("Open", "-------要创建提示框--------");
                            AlertDialog.Builder builder_wifiopen=new AlertDialog.Builder(LycActivity.this);
                            builder_wifiopen.setTitle("提示");
                            builder_wifiopen.setMessage("确定开启名为\""+wifiaround.name+"\"的热点？");
                            builder_wifiopen.setCancelable(true);
                            builder_wifiopen.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    WifiManager wifi_control = (WifiManager)LycActivity.this.getSystemService(Context.WIFI_SERVICE);
                                    //针对6.0开启WRITE_PERMISSION权限
                                    setBrightnessMode(LycActivity.this,1);
                                    //如果热点处于开启状态，则先关闭之
                                    if(isWifiApEnabled(wifi_control)){
                                        closeWifiAp(LycActivity.this);
                                    }
                                    //如果wifi处于打开状态，则关闭wifi
                                    if(wifi_control.isWifiEnabled()){
                                        wifi_control.setWifiEnabled(false);
                                    }
                                    startWifiAp(wifiaround.name,"",true,wifi_control);
                                    //显示关闭热点按钮
                                    button_wifi_close.setVisibility(View.VISIBLE);
//                                    button_wifi_close.setVisibility(View.VISIBLE);
//                                    WifiConfiguration wifiopen=new WifiConfiguration();
//                                    wifiopen.SSID=wifiaround.name;
////                                    wifiopen.preSharedKey="";
////                                    wifiopen.hiddenSSID=false;
////                                    wifiopen.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
////                                    wifiopen.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
//                                    wifiopen.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
////                                    wifiopen.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
//                                    wifiopen.status=WifiConfiguration.Status.ENABLED;
//                                    //通过反射调用设置热点
//                                    try{
//                                        Method method = wifi_control.getClass().getMethod("setWifiApEnabled",WifiConfiguration.class,Boolean.TYPE);
//                                        boolean enable=(Boolean) method.invoke(wifi_control,wifiopen,true);
//                                        if (enable) {
//                                            Toast.makeText(LycActivity.this,"热点已开启 SSID："+wifiaround.name,Toast.LENGTH_SHORT).show();
//                                            button_wifi_close.setVisibility(View.VISIBLE);
//                                        }
//                                        else{
//                                            Toast.makeText(LycActivity.this,"创建热点失败",Toast.LENGTH_SHORT).show();
//                                        }
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                        Toast.makeText(LycActivity.this,"创建热点失败",Toast.LENGTH_SHORT).show();
//                                    }
                                    dialogInterface.dismiss();
                                }
                            });
                            builder_wifiopen.setNegativeButton("还没想好",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder_wifiopen.show();
//                            Log.i("Open", "-------创建提示框之后--------");
                        }
                        else{
                            Toast.makeText(LycActivity.this,"该WiFi已加密，不能伪造……",Toast.LENGTH_SHORT).show();
                        }

//                        Toast.makeText(LycActivity.this,"信号强度"+wifiaround.level,Toast.LENGTH_SHORT).show();
//                        switch (wifiaround.getStatus()){
//                            case "当前连接":
//                                Toast.makeText(LycActivity.this,"当前连接",Toast.LENGTH_SHORT).show();
//                                break;
//                            case "禁用":
//                                Toast.makeText(LycActivity.this,"禁用",Toast.LENGTH_SHORT).show();
//                                break;
//                            case "可用":
//                                Toast.makeText(LycActivity.this,"可用",Toast.LENGTH_SHORT).show();
//                                break;
//                        }

                    }
                });
            }
            public void initWifiAround(WifiAround[] data_around){

                for (int i=0;i<data_around.length;i++) {
                    WifiAround lyc = new WifiAround(data_around[i].name,data_around[i].bssid,data_around[i].keymanagement,data_around[i].level);
                    wifiAroundList.add(lyc);
                }

            }
        });
    }
    public boolean isWifiApEnabled(WifiManager mWifiManager){
        try {
            Method method=mWifiManager.getClass().getMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (boolean) method.invoke(mWifiManager);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void closeWifiAp(Context mContext){
        WifiManager wifiManager= (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        if (isWifiApEnabled(wifiManager)){
            try {
                Method method=wifiManager.getClass().getMethod("getWifiApConfiguration");
                method.setAccessible(true);
                WifiConfiguration config= (WifiConfiguration) method.invoke(wifiManager);
                Method method2=wifiManager.getClass().getMethod("setWifiApEnabled",WifiConfiguration.class,boolean.class);
                method2.invoke(wifiManager,config,false);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    public void startWifiAp(String mSSID,String mPasswd,boolean isOpen,WifiManager mWifiManager){
        Method method1=null;
        try {
            method1=mWifiManager.getClass().getMethod("setWifiApEnabled",
                    WifiConfiguration.class,boolean.class);
            WifiConfiguration netConfig=new WifiConfiguration();

            netConfig.SSID=mSSID;
            netConfig.preSharedKey=mPasswd;
            netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            if (isOpen) {
                netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            }else {
                netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            }
            netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            boolean enable=(Boolean)method1.invoke(mWifiManager,netConfig,true);
            if (enable) {
                 Toast.makeText(LycActivity.this,"热点已开启。名称为："+mSSID,Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(LycActivity.this,"创建热点失败……",Toast.LENGTH_SHORT).show();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Toast.makeText(LycActivity.this,"NoSuchMethodException",Toast.LENGTH_SHORT).show();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Toast.makeText(LycActivity.this,"InvocationTargetException",Toast.LENGTH_SHORT).show();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Toast.makeText(LycActivity.this,"IllegalAccessException",Toast.LENGTH_SHORT).show();
        }
    }
//    public void checkPermission(){
//        if(!Settings.System.canWrite(this)){
//            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
//                    Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, 1);
//        }
//    }
    private void setBrightnessMode(Context context, int mode) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.System.canWrite(context)) {
                    Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, mode);
                } else {
                    Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    intent.setData(Uri.parse("package:" + context.getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
//                    Log.i("Open", "申请权限");
                }
            } else {
                Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, mode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



