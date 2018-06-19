package com.example.administrator.view_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.view_test.Base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

public class buttonActivity extends BaseActivity {

    @BindView(R.id.photoview_img)
    public PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photoView.setImageResource(R.drawable.asd);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_button;
    }

    @Override
    public void initPresenter() {

    }
}
