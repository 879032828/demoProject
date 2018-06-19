package com.example.administrator.view_test;

import android.app.Application;
import android.util.Log;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.view_test.ExceptionHandler.CrashHandler;
import com.example.administrator.view_test.baiduSDK.MyLocationListener;
import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class MyApplication extends Application {

    public static final String RXJAVA_DEBUG = "rxjava_debug";
    public static final String EVENT_DEBUG = "event_debug";
    public static final String PAGER_DEBUG = "pager_debug";
    public static final String FLOATING_DEBUG = "floating_debug";
    public static final String H5_DEBUG = "h5_debug";
    public static final String CUSTOM_DEBUG = "custom_debug";

    public static MyApplication myApplication;



    public static MyApplication getInstant(){
        if (myApplication == null){
            myApplication = new MyApplication();
        }
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //1、添加SD卡读取权限
        //2、实例化崩溃日志处理器
        //3、注册初始化crashHandler
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext(), false);


        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);



    }


}
