package com.cccisi.privacycollector.lyc;

public class WifiAround {

    public String name;
    public String bssid;
    public String keymanagement;
    public int level;

    public WifiAround(String name, String bssid, String keymanagement, int level){
        this.name=name;
        this.bssid=bssid;
        this.keymanagement=keymanagement;
        this.level=level;
    }

    public String getName(){
        return  name;
    }

    public String getBssid(){
        return  bssid;
    }

    public String getKeymanagement(){
        return  keymanagement;
    }
    public int getLevel(){
        return  level;
    }
}
