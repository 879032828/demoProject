package com.example.administrator.view_test.dialog;

import android.app.Activity;
import android.app.Dialog;

/**
 * 创建分享Dialog的工厂
 * Created by c on 2017/8/5.
 */

public class ShareDialogFactory {


    /**
     * 白色背景分享对话框
     *
     * @param activity
     * @return
     */
    public static Dialog createWhiteShareDialog(Activity activity, OnShareItemClickListener shareItemClickListener) {
        return new ShareWhiteDialog.Builder(activity)
                .showReportView(false)
                .showDownloadView(false)
                .setShareItemClickListener(shareItemClickListener)
                .create();
    }


}
