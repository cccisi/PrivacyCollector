package com.cccisi.privacycollector.czh.Bean;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * @author cccis FB:Zachary Chen
 * @name PrivacyCollector
 * @class name：com.cccisi.privacycollector.czh.Bean
 * @class describe
 * @time 4/20/2018 10:20 PM
 * @change
 * @chang time
 * @class describe
 */
public class BaseWebView extends WebView {

    private OnTouchScreenListener onTouchScreenListener;

    public BaseWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public BaseWebView(Context context) {
        super(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onTouchScreen();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onReleaseScreen();
        }

        return super.onTouchEvent(event);
    }

    public void setOnTouchScreenListener(OnTouchScreenListener onTouchScreenListener) {
        this.onTouchScreenListener = onTouchScreenListener;
    }

    public interface OnTouchScreenListener {
        void onTouchScreen();

        void onReleaseScreen();
    }
}
