package com.cccisi.privacycollector.czh;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cccisi.privacycollector.MainActivity;
import com.cccisi.privacycollector.NoteActivity;
import com.cccisi.privacycollector.R;
import com.cccisi.privacycollector.czh.Bean.BaseWebView;
import com.cccisi.privacycollector.czh.Util.FileOperator;
import com.cccisi.privacycollector.czh.Util.KeyboardWatcher;

import butterknife.ButterKnife;

import static com.cccisi.privacycollector.czh.Util.Constant.getUrl;
import static com.cccisi.privacycollector.czh.Util.Constant.setUrl;

/**
 * Created by cccis on 2018/4/12.
 * CZH负责
 * 功能介绍：
 */

public class CzhActivity extends Activity implements KeyboardWatcher.OnKeyboardToggleListener, View.OnClickListener {

    public static int FLAG = 0; //0表示键盘关闭,1表示键盘启动
    private final String TAG = "CzhActivity";
    ImageView mSearchBack;
    EditText mSearchEdit;
    Button mSearchButton;
    BaseWebView mWeb;
    FloatingActionButton mFlb;
    //    String url = new String("http://www.csdn.net");
    private Context mContext;
    private KeyboardWatcher keyboardWatcher;

//    public CzhActivity() {
//    }
//
//    public CzhActivity CzhActivity(String url) {
//        CzhActivity czh = new CzhActivity();
//        czh.url = url;
//        return czh;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czh);
        ButterKnife.bind(this);
        mContext = CzhActivity.this;

        //初始化界面
        initLayout();

        keyboardWatcher = new KeyboardWatcher(this);
        keyboardWatcher.setListener(this);

        mWeb.setFocusable(true);
        mWeb.requestFocus();
        mWeb.setFocusableInTouchMode(true);
        /*获取焦点*/
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
        /*EditText获取输入*/
        mSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //判断是否是“GO”键
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    Toast.makeText(mContext, "点击Go", Toast.LENGTH_LONG).show();
//                    IBinder imBinder = ServiceManager.getService("input");
//                    InputManager im = (InputManager) getSystemService(Context.INPUT_SERVICE);

////inject key event
//                    final KeyEvent keyEvent = new KeyEvent(downTime, eventTime, action,
//                            code, repeatCount, metaState, deviceId, scancode,
//                            flags | KeyEvent.FLAG_FROM_SYSTEM |KeyEvent.FLAG_KEEP_TOUCH_MODE | KeyEvent.FLAG_SOFT_KEYBOARD,
//                            source);
//                    event.setSource(InputDevice.SOURCE_ANY)
//                    im.injectInputEvent(keyEvent, InputManager.INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH);

                    //inject pointer event
//                    motionEvent.setSource(InputDevice.SOURCE_TOUCHSCREEN);
//                    im.injectInputEvent(motionEvent, InputManager.INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH);

                    String temp = mSearchEdit.getText().toString(); //转换为String数据;
                    setWeb(mContext, mWeb, temp);
                    mSearchEdit.setText(temp);
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }
                    // 对应逻辑操作
                    return true;
                }
                return false;
            }
        });
    }

    private void initLayout() {
        //设置状态栏的颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#111e32"));
        }
        mWeb = (BaseWebView) findViewById(R.id.web);
        mSearchButton = (Button) findViewById(R.id.searchButton);
        mSearchButton.setOnClickListener(this);
        mSearchEdit = (EditText) findViewById(R.id.searchEdit);
        mSearchEdit.setOnClickListener(this);
        mSearchBack = (ImageView) findViewById(R.id.searchBack);
        mSearchBack.setOnClickListener(this);
        mFlb = (FloatingActionButton) findViewById(R.id.fab);
        mFlb.setOnClickListener(this);
        setWeb(mContext, mWeb, getUrl());
        mSearchEdit.setText(getUrl());

        /*web触摸事件*/
//        mWeb.setOnTouchScreenListener(new BaseWebView.OnTouchScreenListener() {
//
//            @Override
//            public void onTouchScreen() {
////                isFlowing = true;
////                if (flowBottomLL.getVisibility() == View.GONE) {
////                    flowBottomLL.startAnimation(flowbottomLLAppearAS);
////                    flowBottomLL.setVisibility(View.VISIBLE);
////                }
//            }
//
//            @Override
//            public void onReleaseScreen() {
////                isFlowing = false;
////                if (flowBottomLL.getVisibility() == View.VISIBLE) {
////                }
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        keyboardWatcher.destroy();
        super.onDestroy();
    }


    /**
     * @Author: Zachary Chen
     * @Date: 4/21/2018 2:51 PM
     * @Description: 键盘打开
     */
    @Override
    public void onKeyboardShown(int keyboardSize) {
        FLAG = 1;
        Toast.makeText(mContext, "你打开了键盘!", Toast.LENGTH_SHORT).show();
    }

    /**
     * @Author: Zachary Chen
     * @Date: 4/21/2018 2:51 PM
     * @Description: 键盘关闭
     */
    @Override
    public void onKeyboardClosed() {
        FLAG = 0;
        Toast.makeText(mContext, "键盘关闭状态!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(android.view.MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("---action down-----");
                Toast.makeText(mContext, "起始位置为：" + "(" + event.getX() + " , " + event.getY() + ")", Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("---action up-----");
                Toast.makeText(mContext, "最后位置为：" + "(" + event.getX() + " , " + event.getY() + ")", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchBack:
                Intent intent = new Intent(CzhActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.searchButton:
                String temp = mSearchEdit.getText().toString(); //转换为String数据;
                setWeb(mContext, mWeb, temp);
                mSearchEdit.setText(temp);
                break;
            case R.id.searchEdit:
                mSearchEdit.setText("http://www.");
                mSearchEdit.setSelection("http://www.".length());//将光标移至文字末尾
                break;
            case R.id.fab:
                Intent intent_fab = new Intent(CzhActivity.this, NoteActivity.class);
                startActivity(intent_fab);

        }
    }

//    @OnClick({R.id.searchBack, R.id.searchEdit, R.id.searchButton, R.id.web})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//
////            case R.id.clearButton:
////                mSearchEdit.setText("http://www.");
////                break;
////            case R.id.web:
////                mSearchEdit.setText(mWeb.getUrl());
////                break;
//        }
//    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mWeb.canGoBack()) {
//                mWeb.goBack();//返回上一页面
//                return true;
//            } else {
//                System.exit(0);//退出程序
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * @Author: Zachary Chen
     * @Date: 4/21/2018 2:36 PM
     * @Description: 设置web页面本地打开, 浏览记录
     */
    public WebView setWeb(final Context mContext, WebView web, String url) {
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

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                setUrl(url);
                mSearchEdit.setText(getUrl());
                savePrivacy(url);
                System.out.println("onPageStarted: " + url);
                //实现接口方法并取出数据到外部

                if (url.indexOf("login") != -1) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    FakeFragment fakeFragment = new FakeFragment();
                    transaction.add(R.id.fl_content, fakeFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                setUrl(url);
                System.out.println("onPageFinished: " + url);
                super.onPageFinished(view, url);
            }
            /*JS源*/
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                Constant.url = url;
//                System.out.println("onLoadResource: "+url);
//                super.onLoadResource(view, url);
//            }
        });
        return web;
    }

    private void savePrivacy(String str) {
        FileOperator.createFolder();
        FileOperator.createFile();
        FileOperator.writestring(str);
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