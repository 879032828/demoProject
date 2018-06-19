package com.example.administrator.view_test.VIewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class MyLinearLayoutB extends LinearLayout {
    public MyLinearLayoutB(Context context) {
        super(context);
    }

    public MyLinearLayoutB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayoutB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("lizhenwei", "ViewGroup B dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("lizhenwei", "ViewGroup B onInterceptTouchEvent" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("lizhenwei", "ViewGroup B onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }
}
