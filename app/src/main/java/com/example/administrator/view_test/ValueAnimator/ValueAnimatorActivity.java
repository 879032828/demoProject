package com.example.administrator.view_test.ValueAnimator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.view_test.R;
import com.example.administrator.view_test.Base.BaseActivity;

import butterknife.BindView;


public class ValueAnimatorActivity extends BaseActivity {

    @BindView(R.id.button)
    public Button button;

    @BindView(R.id.img1)
    public ImageView img1;

    @BindView(R.id.img2)
    public ImageView img2;

    class ValueObject {
        float alphaValue;   //透明度的值
        float scaleValue;   //伸缩变化的值

        public ValueObject(float alphaValue, float scaleValue) {
            this.alphaValue = alphaValue;
            this.scaleValue = scaleValue;
        }
    }

    class MyEvaluator implements TypeEvaluator<ValueObject> {

        // 属性动画封装了一个因子fraction，我们设置动画时需要setDuration(xxxx)，例如时间为1000ms，那么当到达100ms时，fraction就为0.1
        // fraction也就是当前时间占总时间的百分比，startValue和endValue就是我们传入的初始值和结束值
        @Override
        public ValueObject evaluate(float fraction, ValueObject startValue, ValueObject endValue) {
            // 计算某个时刻的alpha值和scale值。类似速度公式Vt = V0 + at
            float nowAlphaValue = startValue.alphaValue + (endValue.alphaValue - startValue.alphaValue) * fraction;
            float nowScaleValue = startValue.scaleValue + (endValue.scaleValue - startValue.scaleValue) * fraction;
            return new ValueObject(nowAlphaValue, nowScaleValue);
        }
    }

    public void objectShowAnimation() {
        // 初始alpha值为1，scale值为1
        ValueObject startObjectVal = new ValueObject(0f, 0.1f);
        // 结束alpha值为0，scale值为2，相当于透明度变为0，尺寸放大到2倍
        ValueObject endObjectVal = new ValueObject(1f, 1f);
        MyEvaluator myEvaluator = new MyEvaluator();

        final ValueAnimator animator = ValueAnimator.ofObject(myEvaluator, startObjectVal, endObjectVal);
        animator.setDuration(1000);
        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                img1.setVisibility(View.VISIBLE);
                img1.setImageResource(R.drawable.asd);
                img1.setAlpha(((ValueObject) animation.getAnimatedValue()).alphaValue);
                img1.setScaleType(ImageView.ScaleType.CENTER);
                img1.setScaleX(((ValueObject) animation.getAnimatedValue()).scaleValue);
                img1.setScaleY(((ValueObject) animation.getAnimatedValue()).scaleValue);
            }
        });
    }

    public void objectHideAnimation() {
        // 初始alpha值为1，scale值为1
        ValueObject startObjectVal = new ValueObject(1f, 0.1f);
        // 结束alpha值为0，scale值为2，相当于透明度变为0，尺寸放大到2倍
        ValueObject endObjectVal = new ValueObject(0f, 1f);
        MyEvaluator myEvaluator = new MyEvaluator();

        final ValueAnimator animator = ValueAnimator.ofObject(myEvaluator, startObjectVal, endObjectVal);
        animator.setDuration(1000);
        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                img2.setAlpha(((ValueObject) animation.getAnimatedValue()).alphaValue);
                img2.setScaleType(ImageView.ScaleType.CENTER);
                img2.setScaleX(((ValueObject) animation.getAnimatedValue()).scaleValue);
                img2.setScaleY(((ValueObject) animation.getAnimatedValue()).scaleValue);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectShowAnimation();
                objectHideAnimation();
            }
        });

    }

    private void show(){
        // 第一步，创建一个ValueAnimator。直接调用ValueAnimator.ofFloat来初始化，设置开始值和结束值
        final ValueAnimator alphaAnimator = ValueAnimator.ofFloat(0, 1);
        // 设置变化时长
        alphaAnimator.setDuration(1000);
        alphaAnimator.start();

        // 第二步，ValueAnimator设置监听器
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 我们来检查一下这个方法会调用多少次
                Log.i("TAG", "curValue is " + animation.getAnimatedValue());
                // 在ValueAnimator变化的过程中更新控件的透明度
                img1.setAlpha((float) alphaAnimator.getAnimatedValue());
            }
        });
    }

    private void hide(){
        // 第一步，创建一个ValueAnimator。直接调用ValueAnimator.ofFloat来初始化，设置开始值和结束值
        final ValueAnimator alphaAnimator = ValueAnimator.ofFloat(1, 0);
        // 设置变化时长
        alphaAnimator.setDuration(1000);
        alphaAnimator.start();

        // 第二步，ValueAnimator设置监听器
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 我们来检查一下这个方法会调用多少次
                Log.i("TAG", "curValue is " + animation.getAnimatedValue());
                // 在ValueAnimator变化的过程中更新控件的透明度
                img2.setAlpha((float) alphaAnimator.getAnimatedValue());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_valueanimator;
    }

    @Override
    public void initPresenter() {

    }
}
