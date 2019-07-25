package com.example.administrator.view_test.webp;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import butterknife.BindView;

public class webpActivity extends BaseActivity {

    @BindView(R.id.iv_webp)
    public ImageView iv_webp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv_webp.setBackgroundResource(R.mipmap.ic_duanwu_bg);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_webp;
    }

    @Override
    public void initPresenter() {
    }


}
