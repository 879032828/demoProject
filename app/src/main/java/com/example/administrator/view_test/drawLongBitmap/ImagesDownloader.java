package com.example.administrator.view_test.drawLongBitmap;

import android.content.Context;
import android.text.TextUtils;

import com.example.administrator.view_test.util.ImageLoader;

import java.io.File;
import java.util.List;


public class ImagesDownloader {
    private Context context;

    private DownloadCallback downloadCallback;
    /**
     * 图片url数组
     */
    private String[] urls;
    /**
     * 已下载图片地址l数组
     */
    private String[] paths;
    /**
     * 已完成下载的数量
     */
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

        download(urls);
    }

    /**
     * 外部调用此方法开始下载
     *
     * @param callback
     * @param urls
     */
    public void start(DownloadCallback callback, List<String> urls) {
        this.downloadCallback = callback;
        for (int i = 0, count = urls.size(); i < count; i++) {
            if (TextUtils.isEmpty(urls.get(i))) {
                throw new IllegalArgumentException("one of the startDownload urls is empty ");
            }
        }
        this.urls = urls.toArray(new String[urls.size()]);
        this.paths = new String[urls.size()];

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

            @Override
            public void onFileFailed(Exception e) {
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

                @Override
                public void onFileFailed(Exception e) {
                    if (downloadCallback != null) {
                        downloadCallback.onFalied(e);
                    }
                }
            });
        }
    }

    /**
     * 多个同时异步下载，速度更快
     *
     * @param urls
     */
    private void download(List<String> urls) {
        for (int i = 0; i < urls.size(); i++) {
            final int pos = i;
            ImageLoader.getInstance().downloadFile(context, urls.get(i), new ImageLoader.FileDownloadCallback() {
                @Override
                public void onFileReady(File file) {
//                    LogUtils.w("Download", "pos:" + pos + ", path:" + file.getAbsolutePath());
                    paths[pos] = file.getAbsolutePath();
                    indexAutoIncrement();
                }

                @Override
                public void onFileFailed(Exception e) {
                    if (downloadCallback != null) {
                        downloadCallback.onFalied(e);
                    }
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

        /**
         * @param e 异常
         */
        void onFalied(Exception e);
    }
}
