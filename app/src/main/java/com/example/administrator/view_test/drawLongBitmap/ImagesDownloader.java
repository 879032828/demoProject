package com.example.administrator.view_test.drawLongBitmap;

import android.content.Context;
import android.text.TextUtils;

import com.example.administrator.view_test.util.ImageLoader;

import java.io.File;


public class ImagesDownloader {
    private Context context;
    private String[] urls;
    private DownloadCallback downloadCallback;
    private String[] paths;
    private int index = 0;

    public ImagesDownloader(Context context) {
        this.context = context;
    }

    public static ImagesDownloader newInstance(Context context) {
        return new ImagesDownloader(context);
    }

    /**
     * 外部调用此方法开始下载
     *
     * @param callback
     * @param urls
     */
    public void start(DownloadCallback callback, String... urls) {
        this.downloadCallback = callback;
        for (int i = 0, count = urls.length; i < count; i++) {
            if (TextUtils.isEmpty(urls[i])) {
                throw new IllegalArgumentException("one of the startDownload urls is empty ");
            }
        }
        this.urls = urls;
        this.paths = new String[urls.length];

//        startDownload();
        download(urls);
    }

    /**
     * 逐个顺序下载
     */
    private void download() {
        ImageLoader.getInstance().downloadFile(context, urls[index], new ImageLoader.FileDownloadCallback() {
            @Override
            public void onFileReady(File file) {
                paths[index] = file.getAbsolutePath();
                if (index == urls.length - 1) {
                    if (downloadCallback != null) {
                        downloadCallback.onComplete(paths);
                    }
                } else {
                    index++;
                    download();
                }
            }
        });
    }

    /**
     * 多个同时异步下载，速度更快
     *
     * @param urls
     */
    private void download(String... urls) {
        for (int i = 0; i < urls.length; i++) {
            final int pos = i;
            ImageLoader.getInstance().downloadFile(context, urls[i], new ImageLoader.FileDownloadCallback() {
                @Override
                public void onFileReady(File file) {
//                    LogUtils.w("Download", "pos:" + pos + ", path:" + file.getAbsolutePath());
                    paths[pos] = file.getAbsolutePath();
                    indexAutoIncrement();
                }
            });
        }
    }

    /**
     * 当下载完成之后，调用此方法，会检查文件是否全部下载完毕
     */
    private synchronized void indexAutoIncrement() {
        index++;
        if (index == urls.length && downloadCallback != null) {
            index = 0;
            downloadCallback.onComplete(paths);
        }
    }

    /**
     * 下载结果回调
     */
    public interface DownloadCallback {
        /**
         * @param paths 按下载顺序保存的下载好的文件路径
         */
        void onComplete(String... paths);
    }
}
