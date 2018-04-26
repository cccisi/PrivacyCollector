package com.cccisi.privacycollector;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.cccisi.privacycollector.czh.Util.UtilWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cccis on 2018/4/12.
 * 全体负责
 * 功能介绍：记录功能、心得体会、使用方法
 */

public class NoteActivity extends Activity {

    private final String TAG = "NoteActivit";
    @BindView(R.id.web_note)
    WebView mWebNote;
    //    private String noteUrl = "file:///storage/emulated/0/privacy/pubkey.rc";
    private String noteUrl = "file:///sdcard/privacy/privacy.rc";
//    private String noteUrl =  Environment.getExternalStorageDirectory().getPath()+"privacy/pubkey.rc";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);

        initLayout();
    }

    private void initLayout() {
        UtilWeb.setWeb(mWebNote, noteUrl);
    }
}
