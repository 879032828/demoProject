package com.example.administrator.view_test.splash;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class splashActivity extends BaseActivity {

    @BindView(R.id.button5)
    public Button button5;

    @BindView(R.id.splash_img)
    public ImageView splashImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        countDownTimer.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }

    private CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            button5.setText("跳过（" + millisUntilFinished / 1000 + "s）");
        }

        @Override
        public void onFinish() {
            button5.setText("跳过（" + 0 + "s）");

        }
    };

    @OnClick({R.id.button5, R.id.splash_img})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.splash_img:
//                goToWebViewActivity();

                Splash splash = new Splash(1, "123", "456", "789");
                String path = this.getFilesDir().getPath() + "/img";
                try {
                    SerializableUtil.write(splash, path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Splash splash1 = (Splash) SerializableUtil.read(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.button5:
                Drawable drawable = this.getResources().getDrawable(R.drawable.ic_arrow_upward);
                String path1 = this.getFilesDir().getPath() + "/icon";


//                goToLoginOrMainActivity();
                break;
        }
    }

    public void goToWebViewActivity() {
        String url = "https://www.baidu.com/";
        Intent intent = new Intent(splashActivity.this, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("isfromsplash", true);
        startActivity(intent);
        finish();
    }

    public void goToLoginOrMainActivity() {

    }

    @Override
    protected void onDestroy() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public static final int RC_CAMERA_AND_LOCATION = 123;

    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this,
                    "需要您提供app的权限",
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    Splash splash;

    private void initSplashImage() {
    }

//    private Splash getLocalSplash(){
//        Splash splash = null;
//        String path = this.getFilesDir().getPath() + "img";
//        SerializableUtil.
//    }
}
