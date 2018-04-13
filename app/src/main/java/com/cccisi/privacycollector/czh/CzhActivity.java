package com.cccisi.privacycollector.czh;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.cccisi.privacycollector.R;
import com.cccisi.privacycollector.czh.Util.UtilWeb;

/**
 * Created by cccis on 2018/4/12.
 * CZH负责
 * 功能介绍：
 */

public class CzhActivity extends Activity {

    private final String TAG = "CzhActivit";
    private WebView web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czh);

        initLayout();
    }

    private void initLayout() {
        web = (WebView) findViewById(R.id.web);
        UtilWeb.setWeb(web, "http://www.csdn.net");
//        UtilWeb.setWeb(web,"https://github.com/cccisi/PrivacyCollector/blob/master/README.md");

    }
}
