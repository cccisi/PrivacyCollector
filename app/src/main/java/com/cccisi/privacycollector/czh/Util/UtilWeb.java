package com.cccisi.privacycollector.czh.Util;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by cccis on 2018/4/13.
 */

public class UtilWeb {

    public static WebView setWeb(WebView web, String url) {
        web.loadUrl(url);
        WebSettings webSettings = web.getSettings();

//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存

        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript

        webSettings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
        web.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        return web;
    }

}
