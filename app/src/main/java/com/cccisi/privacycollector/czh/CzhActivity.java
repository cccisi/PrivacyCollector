package com.cccisi.privacycollector.czh;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cccisi.privacycollector.R;

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
//        web.setBackgroundColor(getResources().getColor(R.color.webbackground));
        web.loadUrl("http://www.csdn.net");
        WebSettings webSettings = web.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存

        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
        web.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
    }
}
