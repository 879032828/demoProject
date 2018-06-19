package com.example.administrator.view_test.doubleClick;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by c on 2018/1/6.
 */

public class DoubleClickDetector implements View.OnTouchListener {
    /**
     * 双击间隔
     */
    private static final int DEFAULT_CLICK_INTERVAL = 300;
    /**
     * 标识是否单击过一次了
     */
    private boolean firstClicked = false;
    private int clickInteval;
    private long lastTimeMillis = 0L;
    private DoubleClickCallback doubleClickCallback;
    private SingleClickCallback singleClickCallback;

    private DoubleClickDetector() {
        clickInteval = DEFAULT_CLICK_INTERVAL;
    }

    public static DoubleClickDetector newInstance() {
        return new DoubleClickDetector();
    }

    /**
     * 设置时间间隔值
     *
     * @param clickInteval
     */
    public void setClickInteval(int clickInteval) {
        this.clickInteval = clickInteval;
    }

    public void listen(View view, DoubleClickCallback doubleClickCallback, SingleClickCallback singleClickCallback) {
        if (view == null) {
            return;
        }
        view.setOnTouchListener(this);
        this.doubleClickCallback = doubleClickCallback;
        this.singleClickCallback = singleClickCallback;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                long currentTimeMillis = System.currentTimeMillis();

//                Log.w("DoubleClickDetector", "firstClicked" + firstClicked);
//                if (firstClicked) {
                // 如果是第二次点击，检测两次的时间间隔
                Log.w("DoubleClickDetector", "currentTimeMillis" + currentTimeMillis);
                Log.w("DoubleClickDetector", "lastTimeMillis" + lastTimeMillis);
                if (currentTimeMillis - lastTimeMillis <= clickInteval) {
                    onDoubleClicked(v, event.getRawX(), event.getRawY());
                    Log.w("DoubleClickDetector", "双击");
                    reset();
                    return true;
                }
//                } else {
//                    firstClicked = true;
//                }
                break;
            case MotionEvent.ACTION_MOVE:
                reset();
                break;
            case MotionEvent.ACTION_UP:
                if (System.currentTimeMillis() - lastTimeMillis > clickInteval) {
                    reset();
                }
                break;
        }

        return false;
    }

    public void reset() {
        firstClicked = false;
        lastTimeMillis = 0L;
    }

    private void onDoubleClicked(View view, float x, float y) {
        if (doubleClickCallback != null) {
            doubleClickCallback.onDoubleClick(view, x, y);
        }
    }

    public interface DoubleClickCallback {
        void onDoubleClick(View view, float x, float y);
    }

    private void onSingleClick(View view) {
        if (singleClickCallback != null) {
            singleClickCallback.onSingleClick(view);
        }
    }

    public interface SingleClickCallback {
        void onSingleClick(View view);
    }
}
