package com.example.administrator.view_test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.administrator.view_test.Base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;

public class InstallActivity extends BaseActivity {

    @BindView(R.id.button)
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ServiceApkInstaller(InstallActivity.this, "com.google.ar.core_4d0f7f0f.apk").install();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_install;
    }

    @Override
    public void initPresenter() {

    }

    public class ServiceApkInstaller {
        public final String TAG = ServiceApkInstaller.class.getSimpleName();
        private final String apkPackName = "com.google.ar.core";
        private String apkName;
        private final String newApkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.google.ar.core_4d0f7f0f.apk";
        private Context mContext;
        private Thread subThread;

        public ServiceApkInstaller() {
        }

        public ServiceApkInstaller(Context context, String name) {
            mContext = context;
            apkName = name;
        }

        class installTask implements Runnable {
            @Override
            public void run() {
                if (!hasInstalled()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri uri = FileProvider.getUriForFile(InstallActivity.this, InstallActivity.this.getPackageName() + ".fileprovider", new File(newApkPath));

                    intent.setDataAndType(uri,
                            "application/vnd.android.package-archive");
                    mContext.startActivity(intent);
                }
            }
        }

        public ServiceApkInstaller install() {
            if (subThread != null && subThread.isAlive()) {
                return this;
            }
            subThread = new Thread(new installTask());
            subThread.start();
            return this;
        }

        public void uninstall() {
            Uri packageURI = Uri.parse("package:" + apkPackName);
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            mContext.startActivity(uninstallIntent);
        }


        public boolean hasInstalled() {


            final PackageManager packageManager = mContext.getPackageManager();
            List<PackageInfo> installedPackInfoList = packageManager.getInstalledPackages(0);
            for (int i = 0; installedPackInfoList != null && i < installedPackInfoList.size(); i++) {
                PackageInfo installedPackInfo = installedPackInfoList.get(i);
                if (installedPackInfo != null && TextUtils.equals(apkPackName, installedPackInfo.packageName)) {

                    copyApkFromAssets(mContext, apkName, newApkPath);
                    PackageInfo assetPackInfo = packageManager.getPackageArchiveInfo(newApkPath, PackageManager.GET_ACTIVITIES);
                    if (assetPackInfo != null) {
                        ApplicationInfo appInfo = assetPackInfo.applicationInfo;
                        String assetVersionName = assetPackInfo.versionName;
                        int assetVersionCode = assetPackInfo.versionCode;
                        if (!TextUtils.equals(assetVersionName, installedPackInfo.versionName) || installedPackInfo.versionCode < assetVersionCode) {
                            return false;
                        } else {
                            return true;
                        }
                    }


                    return true;
                }
            }
            return false;
        }

        public boolean copyApkFromAssets(Context context, String fileName, String path) {
            boolean copyIsFinish = false;
            try {
                InputStream is = context.getAssets().open(fileName);
                File file = new File(path);
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] temp = new byte[1024];
                int i = 0;
                while ((i = is.read(temp)) > 0) {
                    fos.write(temp, 0, i);
                }
                fos.close();
                is.close();
                copyIsFinish = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return copyIsFinish;
        }
    }

}
