package com.cccisi.privacycollector.Czh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.cccisi.privacycollector.R;

/**
 * Created by cccis on 2018/4/12.
 */

public class CzhActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czh);

        initLayout();
    }

    private void initLayout() {
        web = (WebView) findViewById(R.id.web);
        web.setBackgroundColor(getResources().getColor(R.color.webbackground));
//        WebChromeClient chorme = new WebChromeClient();
//        web.set
    }
}
