package com.example.administrator.view_test.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.view_test.R;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class ImageLoader {

    private static final String TAG = "ImageLoader";

    private static transient ImageLoader imageLoader;

    private int placeholders[] = {R.drawable.default_place_holder};

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        if (imageLoader == null) {
            synchronized (ImageLoader.class) {
                if (imageLoader == null) {
                    imageLoader = new ImageLoader();
                }
            }
        }
        return imageLoader;
    }

    public int getPlaceholder() {
        Random rand = new Random();
        return placeholders[0];
    }

    public void loadAvatar(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .animate(R.anim.alpha_in)
                .placeholder(getPlaceholder())
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public void loadAvatar(Context context, String url, ImageView imageView, RequestListener requestListener) {
        Glide.with(context)
                .load(url)
                .animate(R.anim.alpha_in)
                .placeholder(getPlaceholder())
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(requestListener)
                .into(imageView);
    }

    public void loadHomeBg(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .animate(R.anim.alpha_in)
                .placeholder(getPlaceholder())
                .error(R.mipmap.user_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 列表中加载图片
     *
     * @param con
     * @param url
     * @param img
     */
    public void loadImageInList(Context con, String url, ImageView img) {
        Glide.with(con).load(url)
                .asBitmap()
                .animate(R.anim.alpha_in)
                .placeholder(getPlaceholder())
                .error(R.drawable.default_place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);
    }

    /**
     * 列表中加载图片
     *
     * @param con
     * @param url
     * @param img
     * @param error
     */
    public void loadImageInList(Context con, String url, ImageView img, int error) {
        Glide.with(con).load(url)
                .animate(R.anim.alpha_in)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);
    }

    public void loadGifInList(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asGif()
                .override(180, 320)
                .animate(R.anim.alpha_in)
                .placeholder(getPlaceholder())
                .error(R.drawable.default_place_holder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public void loadImgFile(Context context, String path, ImageView imageView) {
        if (!TextUtils.isEmpty(path)) {
            loadImgFile(context, new File(path), imageView);
        }
    }

    public void loadImgFile(Context context, File file, ImageView imageView) {
        Glide.with(context).load(file).into(imageView);
    }

    /**
     * 加载资源图片
     *
     * @param con
     * @param resId
     * @param img
     */
    public void loadImage(Context con, int resId, ImageView img) {
        Glide.with(con).load(resId)
                .asBitmap()
                .animate(R.anim.alpha_in)
                .placeholder(getPlaceholder())
                .error(R.drawable.default_place_holder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img);
    }

    /**
     * 加载图片，不缓存
     *
     * @param context
     * @param path
     * @param imageView
     */
    public void loadImgFileWithoutCache(Context context, String path, ImageView imageView) {
        if (!TextUtils.isEmpty(path)) {
            loadImgFileWithoutCache(context, new File(path), imageView);
        }
    }

    /**
     * 加载图片，不缓存
     *
     * @param context
     * @param file
     * @param imageView
     */
    public void loadImgFileWithoutCache(Context context, File file, ImageView imageView) {
        Glide.with(context).load(file).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }

    public void downloadBitmap(Context context, String url, final BitmapDownloadCallback downloadCallback) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        downloadCallback.onBitmapReady(resource);
                    }
                });
    }

    public void downloadFileWithCallback(Context context, String url, final FileDownloadCallback fileDownloadCallback) {
        Glide.with(context)
                .load(url)
                .downloadOnly(new DownloadImageTarget());
    }

    public void downloadFile(Context context, String url, final FileDownloadCallback fileDownloadCallback) {
        Glide.with(context)
                .load(url)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                        if (fileDownloadCallback != null) {
                            fileDownloadCallback.onFileReady(resource);
                        }
                    }
                });
    }

    /**
     * 同步下载文件，需要自己开启线程处理
     *
     * @param context
     * @param url
     * @return
     */
    public File downLoadFile(Context context, String url) {
        try {
            return Glide.with(context)
                    .load(url)
                    .downloadOnly(10, 10)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface BitmapDownloadCallback {
        void onBitmapReady(Bitmap bitmap);
    }

    public interface FileDownloadCallback {
        void onFileReady(File file);
    }

    public static byte[] getBytesByBitmap(Bitmap bitmap) {
        ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
        return buffer.array();
    }

    public class DownloadImageTarget extends BaseTarget<File> {

        @Override
        public void onLoadStarted(Drawable placeholder) {
            Log.d(TAG, "onLoadStarted");
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            Log.d(TAG, "onLoadFailed");
        }

        @Override
        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
            Log.d(TAG, "onResourceReady");
        }

        @Override
        public void onLoadCleared(Drawable placeholder) {
            Log.d(TAG, "onLoadCleared");
        }

        @Override
        public void getSize(SizeReadyCallback cb) {
            Log.d(TAG, "getSize");
        }

        @Override
        public void setRequest(Request request) {
            Log.d(TAG, "setRequest");
        }

        @Override
        public Request getRequest() {
            Log.d(TAG, "getRequest");
            return null;
        }

        @Override
        public void onStart() {
            Log.d(TAG, "onStart");
        }

        @Override
        public void onStop() {
            Log.d(TAG, "onStop");
        }

        @Override
        public void onDestroy() {
            Log.d(TAG, "onDestroy");
        }
    }

}
