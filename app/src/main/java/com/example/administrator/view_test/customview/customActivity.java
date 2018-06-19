package com.example.administrator.view_test.customview;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.customview.LeafLoading.LeafLoadingView;

import java.util.Random;

public class customActivity extends AppCompatActivity {

    private LeafLoadingView leafLoadingView;
    private static final int REFRESH_PROGRESS = 1;
    private int mProgress = 0;
    private MyThread myThread;

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress >= 100)
                        mProgress = 0;

                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
//                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
//                                new Random().nextInt(800));
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                10);
                        leafLoadingView.setProgress(mProgress);
                    } else {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
//                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
//                                new Random().nextInt(1200));
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                10);
                        leafLoadingView.setProgress(mProgress);

                    }
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        leafLoadingView = (LeafLoadingView) findViewById(R.id.leafLoadingView);
        leafLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(REFRESH_PROGRESS);
            }
        });
        myThread = new MyThread();
        myThread.start();
        myThread.mHandler.sendEmptyMessage(1);
    }

    private class MyThread extends Thread{
        public Handler mHandler;

        public MyThread() {
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.d(MyApplication.CUSTOM_DEBUG, "MyThread mHandler");
                    try {
                        Thread.sleep(1000);
                        mHandler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }

        @Override
        public void run() {
            super.run();
            Looper.prepare();


            Looper.loop();
        }
    }

}
