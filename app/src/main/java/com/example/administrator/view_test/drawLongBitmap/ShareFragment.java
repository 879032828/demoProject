package com.example.administrator.view_test.drawLongBitmap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.dialog.OnShareItemClickListener;
import com.example.administrator.view_test.dialog.ShareDialogFactory;
import com.example.administrator.view_test.dialog.ShareType;
import com.example.administrator.view_test.dialog.ShareWhiteDialog;
import com.example.administrator.view_test.drawLongBitmap.data.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.chromium.base.ThreadUtils.runOnUiThread;

public class ShareFragment extends DialogFragment implements View.OnClickListener {

    private static String TAG = "ShareFragment";

    private Activity mContext;
    private Fragment mFragment;

    private ImageView ivLongPicture;
    private LinearLayout llPosterLoading;

    private MyAllDrawLongPictureUtil drawLongPictureUtil;
    private String resultPath;

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragment = this;
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        startLoading();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void initData() {
        handleArguments();
    }

    private void startLoading() {
        drawLongPictureUtil = new MyAllDrawLongPictureUtil(mContext);
        drawLongPictureUtil.setListener(new MyAllDrawLongPictureUtil.Listener() {
            @Override
            public void onSuccess(String path) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext.getApplicationContext(),
                                "已经生成",
                                Toast.LENGTH_LONG).show();
                        llPosterLoading.setVisibility(View.GONE);
                        Glide.with(mContext)
                                .load(resultPath)
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher)
                                .into(ivLongPicture);
                    }
                });
                resultPath = path;
            }

            @Override
            public void onFail() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        llPosterLoading.setVisibility(View.GONE);
                    }
                });
            }
        });
        Info info = new Info();
        info.setContent("这是内容");
        List<String> ImageList = new ArrayList<String>();
        ImageList.add("http://thirdqq.qlogo.cn/g?b=oidb&k=4xuEcb5LLaKN9icPuwW9kcg&s=100");
        ImageList.add("http://thirdqq.qlogo.cn/g?b=oidb&k=4xuEcb5LLaKN9icPuwW9kcg&s=100");
        ImageList.add("http://thirdqq.qlogo.cn/g?b=oidb&k=4xuEcb5LLaKN9icPuwW9kcg&s=100");
        ImageList.add("http://thirdqq.qlogo.cn/g?b=oidb&k=4xuEcb5LLaKN9icPuwW9kcg&s=100");
        info.setImageList(ImageList);
        drawLongPictureUtil.setData(info);
        drawLongPictureUtil.startDraw();
    }

    private void handleArguments() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ShareWhiteDialog dialog = (ShareWhiteDialog) ShareDialogFactory.createWhiteShareDialog(mContext, new OnShareItemClickListener() {
            @Override
            public void onShareItemClick(Dialog dialog, ShareType type) {
                switch (type) {
                    case TYPE_LONG_PICTURE:
                        ShowPhotoViewActivity.start(mContext, resultPath);
                        break;
                }
            }
        });
        initView(Objects.requireNonNull(dialog.getWindow()).getDecorView(), savedInstanceState);
        initListener();
        return dialog;
    }

    private void initView(View view, Bundle savedInstanceState) {
        ivLongPicture = view.findViewById(R.id.iv_long_picture);
        llPosterLoading = view.findViewById(R.id.ll_poster_loading);
        llPosterLoading.setVisibility(View.VISIBLE);
    }

    private void initListener() {
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
