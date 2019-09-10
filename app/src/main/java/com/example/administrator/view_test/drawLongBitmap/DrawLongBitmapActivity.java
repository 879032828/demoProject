package com.example.administrator.view_test.drawLongBitmap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.drawLongBitmap.data.Info;

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
            public void onClick(View v) {
//                drawLongPictureUtil = new MyAllDrawLongPictureUtil(DrawLongBitmapActivity.this);
//                drawLongPictureUtil.setListener(new MyAllDrawLongPictureUtil.Listener() {
//                    @Override
//                    public void onSuccess(String path) {
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(DrawLongBitmapActivity.this.getApplicationContext(),
//                                        "已经生成",
//                                        Toast.LENGTH_LONG).show();
//                            }
//                        });
//                        resultPath = path;
//                    }
//
//                    @Override
//                    public void onFail() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                            }
//                        });
//                    }
//                });
//                Info info = new Info();
//                info.setContent("这是内容");
//                info.setImageList(new ArrayList<String>());
//                drawLongPictureUtil.setData(info);
//                drawLongPictureUtil.startDraw();
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
