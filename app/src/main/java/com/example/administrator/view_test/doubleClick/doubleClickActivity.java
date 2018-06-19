package com.example.administrator.view_test.doubleClick;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import butterknife.BindView;

public class doubleClickActivity extends BaseActivity implements DoubleClickDetector.DoubleClickCallback, DoubleClickDetector.SingleClickCallback {

    @BindView(R.id.full_screen)
    public View full_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DoubleClickDetector.newInstance().listen(full_screen, this, this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_double_click;
    }

    @Override
    public void initPresenter() {
        double i = Math.tan(90);
        double j = Math.sin(Math.PI/6);
        Log.w("doubleClickActivity", "PI : " + Math.PI/6);
        Log.w("doubleClickActivity", "i : " + j);
    }

    @Override
    public void onDoubleClick(View view, float x, float y) {

    }

    @Override
    public void onSingleClick(View view) {

    }
}
