package com.example.administrator.view_test.PropertyAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.view_test.R;

import java.util.Timer;
import java.util.TimerTask;

public class AnimationActivity extends AppCompatActivity {

    private Timer timer;
    private TimerTask timerTask;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private TextView count;

    private LinearLayout mHiddenView;
    private float mDensity;
    private int mHiddenViewMeasuredHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView1 = (ImageView) findViewById(R.id.id_ball);
        imageView2 = (ImageView) findViewById(R.id.id_bal2);
        imageView3 = (ImageView) findViewById(R.id.id_bal3);
        imageView4 = (ImageView) findViewById(R.id.id_bal4);
        imageView5 = (ImageView) findViewById(R.id.id_bal5);
        count = (TextView) findViewById(R.id.count);

        mHiddenView = (LinearLayout) findViewById(R.id.hidden_view);
        mDensity = getResources().getDisplayMetrics().density;

        mHiddenViewMeasuredHeight = (int) (mDensity * 40 + 0.5);
    }

    public void rotateyAnimRun(View view) {

        tvTimer();

    }

    public void llClick(View view) {
        if (mHiddenView.getVisibility() == View.GONE) {
            animateOpen(mHiddenView);
        } else {
            animateClose(mHiddenView);
        }
    }

    private void animateOpen(final View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mHiddenViewMeasuredHeight);
        animator.start();
    }

    private void animateClose(final View view){
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    /**
     * 旋转动画
     *
     * @param view
     */
    public void rotationAnimator(View view) {
        final ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0.0F, 360.0F);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000).setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    /**
     * 如果针对同一个对象的多个属性，要同时作用多种动画，可以使用propertyValuesHolder实现
     *
     * @param view
     */
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 300f, 0f, 300f, 0f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f, 0);
        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f, 0);
        ObjectAnimator.ofPropertyValuesHolder(view, p1, p2, p3).setDuration(3000).start();
    }

    private void startAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(imageView1, "alpha", 1f, 0.5f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView2, "translationY", 200f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView3, "translationX", 200f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView4, "translationY", -200f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageView5, "translationX", -200f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.playTogether(animator0, animator1, animator2, animator3, animator4);
        animatorSet.start();
    }

    /**
     * 计时器动画
     */
    private void tvTimer() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                count.setText("Time " + animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }
}
