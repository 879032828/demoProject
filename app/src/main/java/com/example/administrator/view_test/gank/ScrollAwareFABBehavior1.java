package com.example.administrator.view_test.gank;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.event.UIEvent;
import com.example.administrator.view_test.event.UIEventManager;
import com.example.administrator.view_test.event.UIEventType;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class ScrollAwareFABBehavior1 extends FloatingActionButton.Behavior {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;
    private boolean mIsPageStop = false;

    public ScrollAwareFABBehavior1(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, final FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);

        Log.d(MyApplication.FLOATING_DEBUG, "页面停止滚动");
        Log.d(MyApplication.FLOATING_DEBUG, child.getVisibility() + "");
        Log.d(MyApplication.FLOATING_DEBUG, mIsPageStop + "");

        if (mIsAnimatingOut == false)
            mIsPageStop = true;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                UIEvent event = new UIEvent();
                event.setType(UIEventType.ClearChatLog);
                UIEventManager.getInstance().broadcast(event);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 500);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        mIsPageStop = false;

        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            animateOut(child);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            animateIn(child);
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to hide the FAB when the AppBarLayout exits
    public void animateOut(final FloatingActionButton button) {
        if (Build.VERSION.SDK_INT >= 14) {

            Log.d(MyApplication.FLOATING_DEBUG, "animateOut退出动画");
            Log.d(MyApplication.FLOATING_DEBUG,
                    "动画开始前：left : " + button.getLeft() +
                            "top : " + button.getTop() +
                            "bottom : " + button.getBottom() + "right : " + button.getRight());
            Log.d(MyApplication.FLOATING_DEBUG, "x和y分别是View左上角在父容器中的坐标");
            Log.d(MyApplication.FLOATING_DEBUG, "translationX，translationY则是View当前位置相对于初始化位置的偏移量");
            Log.d(MyApplication.FLOATING_DEBUG,
                    "x : " + button.getX() +
                            "y : " + button.getY() +
                            "translationX : " + button.getTranslationX() + "translationY : " + button.getTranslationY());

            ViewCompat.animate(button).translationY(button.getHeight() + getMarginBottom(button)).setInterpolator(INTERPOLATOR).withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                            ScrollAwareFABBehavior1.this.mIsAnimatingOut = true;
                        }

                        public void onAnimationCancel(View view) {
                            ScrollAwareFABBehavior1.this.mIsAnimatingOut = false;
                        }

                        public void onAnimationEnd(View view) {
                            ScrollAwareFABBehavior1.this.mIsAnimatingOut = false;
                            view.setVisibility(View.INVISIBLE);

                            Log.d(MyApplication.FLOATING_DEBUG,
                                    "动画开始后：left : " + button.getLeft() +
                                            "top : " + button.getTop() +
                                            "bottom : " + button.getBottom() + "right : " + button.getRight());
                            Log.d(MyApplication.FLOATING_DEBUG,
                                    "x : " + button.getX() +
                                            "y : " + button.getY() +
                                            "translationX : " + button.getTranslationX() + "translationY : " + button.getTranslationY());
                        }
                    }).start();


        } else {

        }
    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    public static void animateIn(final FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);

        Log.d(MyApplication.FLOATING_DEBUG, "animateOut进入动画");
        Log.d(MyApplication.FLOATING_DEBUG,
                "动画开始前：left : " + button.getLeft() +
                        "top : " + button.getTop() +
                        "bottom : " + button.getBottom() + "right : " + button.getRight());
        Log.d(MyApplication.FLOATING_DEBUG, "x和y分别是View左上角在父容器中的坐标");
        Log.d(MyApplication.FLOATING_DEBUG, "translationX，translationY则是View当前位置相对于初始化位置的偏移量");
        Log.d(MyApplication.FLOATING_DEBUG,
                "x : " + button.getX() +
                        "y : " + button.getY() +
                        "translationX : " + button.getTranslationX() + "translationY : " + button.getTranslationY());

        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).translationY(0)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {

                }

                @Override
                public void onAnimationEnd(View view) {
                    Log.d(MyApplication.FLOATING_DEBUG,
                            "动画开始后：left : " + button.getLeft() +
                                    "top : " + button.getTop() +
                                    "bottom : " + button.getBottom() + "right : " + button.getRight());
                    Log.d(MyApplication.FLOATING_DEBUG,
                            "x : " + button.getX() +
                                    "y : " + button.getY() +
                                    "translationX : " + button.getTranslationX() + "translationY : " + button.getTranslationY());
                }

                @Override
                public void onAnimationCancel(View view) {

                }
            }).start();
        } else {

        }
    }

    private int getMarginBottom(View v) {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }
}
