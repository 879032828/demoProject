package com.example.administrator.view_test.splash;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.Main2Activity;
import com.example.administrator.view_test.R;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebViewClient;


public class WebViewActivity extends BaseActivity {

    private String mUrl;

    private Boolean isFromSplash;

    private com.tencent.smtt.sdk.WebView WebView;

    private boolean mWebViewError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clearWebViewCookie();

        mUrl = getIntent().getStringExtra("url");

        isFromSplash = getIntent().getBooleanExtra("isfromsplash", false);

        initView();

        initData();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_other;
    }

    @Override
    public void initPresenter() {

    }

    public void initData() {
        WebView.loadUrl(mUrl);
    }

    private void initView() {

        WebView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.zhengyao_webview);

        WebSettings webSettings = WebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            webSettings.setDisplayZoomControls(true);
        }
        webSettings.setDomStorageEnabled(true);

        WebView.addJavascriptInterface(new SnapJsInterface(this, WebView), "brige");

        WebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(com.tencent.smtt.sdk.WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

            //设置响应js 的Confirm()函数
            @Override
            public boolean onJsConfirm(com.tencent.smtt.sdk.WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Confirm");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
                b.create().show();
                return true;
            }

        });

        WebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(com.tencent.smtt.sdk.WebView view, String url, Bitmap favicon) {
                Log.e("正在加载页面中", "");
            }

            @Override
            public void onReceivedError(com.tencent.smtt.sdk.WebView view, int errorCode, String description, String failingUrl) {
                mWebViewError = true;
                WebView.setVisibility(View.GONE);
            }

            @Override
            public void doUpdateVisitedHistory(com.tencent.smtt.sdk.WebView view, String url, boolean isReload) {
                if (WebView.canGoBack()) {
//                    mTitleBar.showLeftLayout();
                } else {
//                    mTitleBar.hideLeftLayout();
                }
            }

            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView view, String url) {
                Log.e("加载页面完成", "");
                if (mWebViewError) {//加载失败
                    WebView.setVisibility(View.GONE);
//                    mNoDataTip.doTipsView("点击刷新");
                    mWebViewError = false;
                } else {
                    WebView.setVisibility(View.VISIBLE);
//                    mNoDataTip.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedSslError(com.tencent.smtt.sdk.WebView webView, SslErrorHandler sslErrorHandler, com.tencent.smtt.export.external.interfaces.SslError sslError) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                sslErrorHandler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (WebView != null && WebView.canGoBack()) {
            WebView.goBack();
            return;
        } else if (isFromSplash) {
            gotoMainActivity();
        } else {
            super.onBackPressed();
        }
        finish();
    }

    public void gotoMainActivity() {
        Intent intent = new Intent(WebViewActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    public static void clearWebViewCookie() {
        try {
            CookieManager cookieManager = CookieManager.getInstance();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                try {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        CookieSyncManager.getInstance().sync();
                    } else {
                        CookieManager.getInstance().flush();
                    }
                } catch (Exception | Error e) {
                    e.printStackTrace();
                }
            }
            cookieManager.removeAllCookie();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }
}
