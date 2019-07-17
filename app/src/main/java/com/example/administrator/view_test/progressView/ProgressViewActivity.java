package com.example.administrator.view_test.progressView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import butterknife.BindView;

public class ProgressViewActivity extends BaseActivity {

    @BindView(R.id.tv_progress)
    public Button tv_progress;

    @BindView(R.id.pv_progress)
    public ProgressView progressView;

    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_progressview;
    }

    @Override
    public void initPresenter() {
        tv_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.setProgress(++progress);
            }
        });
    }

}
