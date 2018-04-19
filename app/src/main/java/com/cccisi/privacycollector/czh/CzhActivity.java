package com.cccisi.privacycollector.czh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cccisi.privacycollector.MainActivity;
import com.cccisi.privacycollector.R;
import com.cccisi.privacycollector.czh.Util.KeyboardWatcher;
import com.cccisi.privacycollector.czh.Util.UtilWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cccis on 2018/4/12.
 * CZH负责
 * 功能介绍：
 */

public class CzhActivity extends Activity implements KeyboardWatcher.OnKeyboardToggleListener {

    private final String TAG = "CzhActivit";
    @BindView(R.id.searchBack)
    ImageView mSearchBack;
    @BindView(R.id.searchEdit)
    EditText mSearchEdit;
    @BindView(R.id.clearButton)
    ImageButton mClearButton;
    @BindView(R.id.searchButton)
    Button mSearchButton;
    @BindView(R.id.web)
    WebView mWeb;
    String url = new String("http://www.csdn.net");
    private KeyboardWatcher keyboardWatcher;

    public CzhActivity() {
    }

    public CzhActivity CzhActivity(String url) {
        CzhActivity czh = new CzhActivity();
        czh.url = url;
        return czh;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czh);
        ButterKnife.bind(this);

        //初始化界面
        initLayout();

        keyboardWatcher = new KeyboardWatcher(this);
        keyboardWatcher.setListener(this);

        /*获取焦点*/
        //        {        web.setFocusable(true);
//        web.requestFocus();
//        web.setFocusableInTouchMode(true);
// }
//        web.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                    case MotionEvent.ACTION_UP:
//                        if (!v.hasFocus()) {
//                            v.requestFocus();
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
    }

    private void initLayout() {
        //设置状态栏的颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#111e32"));
        }
        UtilWeb.setWeb(mWeb, url);
        mSearchEdit.setText(url);
    }

    @Override
    protected void onDestroy() {
        keyboardWatcher.destroy();
        super.onDestroy();
    }

    @Override
    public void onKeyboardShown(int keyboardSize) {

        Log.e(TAG, "onKeyboardShown: 开启键盘");
    }

    @Override
    public void onKeyboardClosed() {
        Log.e(TAG, "onKeyboardClosed: 关闭键盘");

    }

    @OnClick({R.id.searchBack, R.id.searchEdit, R.id.clearButton, R.id.searchButton, R.id.web})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchBack:
                Intent intent = new Intent(CzhActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.clearButton:
                mSearchEdit.setText("");
                break;
            case R.id.searchButton:
                url = mSearchEdit.getText().toString(); //转换为String数据;
                Intent intent2 = new Intent(CzhActivity.this, CzhActivity(url).getClass());
                startActivity(intent2);

//                UtilWeb.setWeb(mWeb, url);
                break;
            case R.id.web:
                mSearchEdit.setText(mWeb.getUrl());
                break;
            case R.id.searchEdit:
                mSearchEdit.setText("");
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWeb.canGoBack()) {
                mWeb.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

        /*捕捉硬键盘*/
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//            /*隐藏软键盘*/
//            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (inputMethodManager.isActive()) {
//                inputMethodManager.hideSoftInputFromWindow(CzhActivity.this.getCurrentFocus().getWindowToken(), 0);
//            }
//
////            edittext.setText("success");
////            webview.loadUrl(URL);
//            return true;
//        }
//        return super.dispatchKeyEvent(event);
//    }

    /*https://www.2cto.com/kf/201405/299290.html*/
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)  {
//        return super.onKeyDown(keyCode, event);
//    }

    /*来源https://blog.csdn.net/fg607/article/details/62218518*/
//    public void onAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
//
//        isClickEditable = false;
//        isClickEditable = false;
//
//        if(accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
//
//            if(accessibilityEvent.getSource() != null){
//
//                isClickEditable = accessibilityEvent.getSource().isEditable();
//
//            }
//
//        }else if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_VIEW_FOCUSED){
//
//
//            if(accessibilityEvent.getSource() != null){
//
//                isFocusedEditable = accessibilityEvent.getSource().isEditable();
//
//            }
//
//        }
//
//        if(isClickEditable || isFocusedEditable){
//
//            //输入法已打开
//
//        }else {
//
//            //输入法已关闭
//        }
//
//    }
}
