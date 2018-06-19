package com.example.administrator.view_test.privateDir;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class privatedirActivity extends BaseActivity {

    @BindView(R.id.privateshow)
    public Button privateshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_privatedir;
    }

    @Override
    public void initPresenter() {

    }

    @OnClick(R.id.privateshow)
    public void showprivate() {
        Log.e("私有目录路径", this.getFilesDir().getPath());
    }

}
