package com.cccisi.privacycollector.lyc;

public class Wifi{
    /*
    Wifi信息：SSID-名称；
              status-2可用/1禁用/0正在使用；
              allowedKeyManagement-加密方式：0-NONE。没有使用WPA; 可以使用明文或静态WEP。
                                             1-WPA_PSK。WPA预共享密钥（需要指定preSharedKey）。
                                             2-WPA_EAP。使用EAP认证的WPA。通常与外部认证服务器一起使用。
                                             3-IEEE8021X。使用EAP认证和（可选）动态生成的WEP密钥
     */
    public String name;
    public String status;
    public String keymanagement;

    public Wifi(String name, String status,String keymanagement){
        this.name=name;
        this.status=status;
        this.keymanagement=keymanagement;
    }

    public String getName(){
        return  name;
    }

    public String getStatus(){
        return  status;
    }

    public String getKeymanagement(){
        return  keymanagement;
    }
}
