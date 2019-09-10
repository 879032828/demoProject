package com.example.administrator.view_test.drawLongBitmap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import java.io.File;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

public class ShowPhotoViewActivity extends BaseActivity {

    @BindView(R.id.photo)
    PhotoView photoView;

    public String realPath;

    public static void start(Context context, String realPath) {
        Intent intent = new Intent(context, ShowPhotoViewActivity.class);
        intent.putExtra("realPath", realPath);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realPath = getIntent().getStringExtra("realPath");
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(ShowPhotoViewActivity.this,
                    ShowPhotoViewActivity.this.getPackageName() + ".fileprovider", new File(realPath));
        } else {
            uri = Uri.fromFile(new File(realPath));
        }
        Glide.with(mContext)
                .load(uri)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(photoView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_photo_view;
    }

    @Override
    public void initPresenter() {

    }
}
