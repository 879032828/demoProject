package com.example.administrator.view_test.VIewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class MyView extends Button{
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("lizhenwei", "View onTouchEvent" + event.getAction());
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("lizhenwei", "View dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }
}
