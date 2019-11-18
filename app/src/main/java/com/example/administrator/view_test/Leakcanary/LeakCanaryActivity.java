package com.example.administrator.view_test.Leakcanary;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.view_test.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class LeakCanaryActivity extends AppCompatActivity {

    /**
     * 1、静态变量
     * 2、不正确的单例模式
     * 3、未关闭/释放资源
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);

        staticVariable();
    }

    /**
     * 1、静态变量
     * <p>
     * 静态变量作为gc root，如果一个对象被static声明，这个对象会一直存活直到程序进程停止
     */
    private static Context context;

    private void staticVariable() {
        context = this;
    }

    /**
     * 2、不正确的单例模式
     * <p>
     * 不正确使用单例模式是引起内存泄露的一个常见问题，
     * 单例对象在被初始化后将在JVM 的整个生命周期中存在(以静态变量的方式)，
     * 如果单例对象持有外部对象的引用，那么这个外部对象将不能被JVM 正常回收，导致内存泄露
     * <p>
     * 这里如果传递Activity作为Context来获得单例对象，那么单例持有Activity的引用，导致Activity不能被释放。
     * 不要直接对 Activity 进行直接引用作为成员变量，如果允许可以使用Application。
     * 如果不得不需要Activity作为Context，可以使用弱引用WeakReference，
     * 相同的，对于Service 等其他有自己声明周期的对象来说，直接引用都需要谨慎考虑是否会存在内存泄露的可能。
     */
    private void SingleInstance() {
        AppManager appManager = AppManager.getInstance(this);
        appManager.test();
    }

    MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();

    /**
     * BraodcastReceiver，ContentObserver，FileObserver，Cursor，Callback等
     * 在 Activity onDestroy 或者某类生命周期结束之后一定要 unregister 或者 close 掉，
     * 否则这个 Activity 类会被 system 强引用，不会被内存回收。
     */
    private void register() {
        this.registerReceiver(myBroadcastReceiver, new IntentFilter(""));
    }

    private void unregister() {
        this.unregisterReceiver(myBroadcastReceiver);
    }


    /**
     * IO流在使用时，有缓冲，使用结束后要关闭，释放资源
     */
    private void stream() {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/测试.txt"));
            fileOutputStream.write("test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @AfterPermissionGranted(123)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
            stream();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this,
                    "需要您提供app的权限",
                    123, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
