package com.cccisi.privacycollector.xsy.Utils;

import android.Manifest;

public class Constant {
    public static final int THIRDAPP = 0;
    public static final int SYSAPP = 1;
    public static final int RUNNING_APP = 2;
    public static final String[] SYS_PERMISSIONS = {Manifest.permission.READ_PHONE_STATE};
    public static final String[] USER_PERMISSIONS = {Manifest.permission.READ_CONTACTS,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET,Manifest.permission.READ_PHONE_STATE};
    public static final String[] APP_PERMISSIONS_LESS_L = {Manifest.permission.GET_TASKS};
    public static final String[] APP_PERMISSIONS_EG_L = {Manifest.permission.PACKAGE_USAGE_STATS};
    public static final String[] SELECT_CONTACT = {"爸爸","妈妈","dad","mom","ba","ma","舅舅","老师","老公","老婆","儿子"};
}
