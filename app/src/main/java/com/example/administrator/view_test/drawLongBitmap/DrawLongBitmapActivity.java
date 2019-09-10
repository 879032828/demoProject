package com.example.administrator.view_test.drawLongBitmap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.drawLongBitmap.data.Info;
import com.example.administrator.view_test.util.ImageLoader;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;

public class DrawLongBitmapActivity extends BaseActivity {

    @BindView(R.id.btn_start)
    Button btn_start;

    private MyAllDrawLongPictureUtil drawLongPictureUtil;

    private String resultPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_draw_long_bitmap;
    }

    @Override
    public void initPresenter() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//http://thirdqq.qlogo.cn/g?b=oidb&k=4xuEcb5LLaKN9icPuwW9kcg&s=100
                ImageLoader.getInstance().downloadFileWithCallback(mContext, "http://thirdqq.qlogo.cn/g?b=oidb&k=4xuEcb5LLaKN9icPuwW9kcg&s=100", new ImageLoader.FileDownloadCallback() {
                    @Override
                    public void onFileReady(File file) {

                    }

                    @Override
                    public void onFileFailed(Exception e) {

                    }
                });
            }
        });

        findViewById(R.id.btn_preview_longpic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareFragment shareFragment = ShareFragment.newInstance();
                shareFragment.show(DrawLongBitmapActivity.this.getSupportFragmentManager(), DrawLongBitmapActivity.class.getSimpleName());

//                if (TextUtils.isEmpty(resultPath)) {
//                    return;
//                }
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri;
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    uri = FileProvider.getUriForFile(DrawLongBitmapActivity.this,
//                            DrawLongBitmapActivity.this.getPackageName() + ".fileprovider", new File(resultPath));
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                } else {
//                    uri = Uri.fromFile(new File(resultPath));
//                }
//                intent.setDataAndType(uri, "image/*");
//                startActivity(intent);
            }
        });
    }
}
