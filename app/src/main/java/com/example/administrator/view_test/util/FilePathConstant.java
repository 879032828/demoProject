package com.example.administrator.view_test.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by shen on 16/4/10.
 */
public class FilePathConstant {
    /**
     * 获取图片保存的路径
     *
     * @return
     */
    public static File getImagePath() {
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/snap_im/images/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    /**
     * 获取图片保存的路径
     * @return
     */
    public static File getAdvertImagePath(){
        File f=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/snap_im/advert_images/");
        if(!f.exists()){
            f.mkdirs();
        }
        return f;
    }

    /**
     * 声音存储路径
     *
     * @return
     */
    public static String getVoicePath() {
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/snap_im/voice/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f.getAbsolutePath() + "/";
    }

    /**
     * 获取视频存储路径
     *
     * @return
     */
    public static String getVideoPath() {
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/snap_im/video/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f.getAbsolutePath() + "/";
    }

    /**
     * 下载文件地址
     */
    public static File getDownloadPath() {
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/snap_im/download/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

    /**
     * 获取错误log保存的文件夹路径
     *
     * @return
     */
    public static File getLogFilePath() {
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/snap_log/");
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }

//    /**
//     * 获取保存用户数据的根目录
//     *
//     * @return
//     */
//    public static File getBaseDataDir() {
//        File f = SnapApplication.getApplication().getFilesDir();
//        return f;
//    }
//
//    /**
//     * 返回当前登录用户的数据的存储目录，如果当前用户没有登录，则返回数据的根目录
//     *
//     * @return
//     */
//    public static File getCurrentUserDataDir() {
//        File f = getBaseDataDir();
//        if (UserProfileManager.getInstance().getCurrentUserInfo() != null) {
//            //创建个人数据对应的文件夹
//            f = new File(f, UserProfileManager.getInstance().getCurrentUserInfo().getUserId());
//            if (!f.exists()) {
//                f.mkdirs();
//            }
//        }
//        return f;
//    }

    public static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }

}
