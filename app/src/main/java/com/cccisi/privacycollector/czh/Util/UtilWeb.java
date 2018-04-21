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
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon){
//                setUrl(url);
//                System.out.println("onPageStarted: "+url);;//实现接口方法并取出数据到外部
//            }
//            /*JS源*/
////            @Override
////            public void onLoadResource(WebView view, String url) {
////                Constant.url = url;
////                System.out.println("onLoadResource: "+url);
////                super.onLoadResource(view, url);
////            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                setUrl(url);
//                System.out.println("onPageFinished: "+url);
//                super.onPageFinished(view, url);
//            }
//
        });

        return web;
    }

}
