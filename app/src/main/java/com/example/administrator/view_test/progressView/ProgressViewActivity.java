package com.example.administrator.view_test.progressView;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import butterknife.BindView;

public class ProgressViewActivity extends BaseActivity {

    @BindView(R.id.tv_progress)
    public Button tv_progress;

    @BindView(R.id.btn_start)
    public Button btn_start;

    @BindView(R.id.pv_progress)
    public ProgressView progressView;

    int progress = 0;

    private ValueProgressView progressview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        progressview2 = (ValueProgressView) findViewById(R.id.progressview2);
        progressview2.setColor(getResources().getColor(R.color.colorPrimary));
        progressview2.setProgress(50);


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressview2.startAnim();
                progressview2.getAnimator().addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        progressview2.startAnim();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_progressview;
    }

    @Override
    public void initPresenter() {
        progress = 385;
        tv_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.setProgress(++progress);
            }
        });
    }


}
