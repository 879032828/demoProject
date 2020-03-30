package com.example.administrator.view_test;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.view_test.Base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

public class buttonActivity extends BaseActivity {

    @BindView(R.id.photoview_img)
    public PhotoView photoView;
    Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photoView.setImageResource(R.drawable.asd);

        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0);
            }
        });

        thread.start();


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_button;
    }

    @Override
    public void initPresenter() {

    }
}
