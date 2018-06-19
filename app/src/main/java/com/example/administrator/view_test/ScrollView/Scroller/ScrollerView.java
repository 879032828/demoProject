package com.example.administrator.view_test.ScrollView.Scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class ScrollerView extends TextView implements View.OnTouchListener {

    private String TAG = "ScrollerView";

    private Context context;
    private Scroller scroller;

    public ScrollerView(Context context) {
        super(context);
        initDataa(context);
    }

    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDataa(context);
    }

    public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDataa(context);
    }

    private void initDataa(Context context) {
        this.context = context;
        this.setOnTouchListener(this);
        scroller = new Scroller(context);
    }

    private void smoothScrollTo(int destX, int destY) {
        int scrollX = this.getScrollX();
        Log.w(TAG, "scrollX = " + scrollX);
        int scrollY = this.getScrollY();
        Log.w(TAG, "scrollY = " + scrollY);
        int deltaX = destX - scrollX;
        Log.w(TAG, "deltaX = " + deltaX);
        int deltaY = destY - scrollY;
        Log.w(TAG, "deltaY = " + deltaY);
        scroller.startScroll(scrollX, scrollY, deltaX, deltaY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            this.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.w(TAG, "getX = " + event.getX());
                Log.w(TAG, "getY = " + event.getY());
                smoothScrollTo(-(int) event.getX(), -(int) event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                smoothScrollTo(-(int) event.getX(), -(int) event.getY());
                break;
        }
        return true;
    }
}
