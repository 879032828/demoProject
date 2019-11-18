package com.example.administrator.view_test.Leakcanary;

import android.content.Context;

public class AppManager {

    private static AppManager appManager;
    private Context context;

    private AppManager(Context context) {
        this.context = context;
    }

    public static AppManager getInstance(Context context) {
        if (appManager == null) {
            appManager = new AppManager(context);
        }
        return appManager;
    }

    public void test(){

    }
}
