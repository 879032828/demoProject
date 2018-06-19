package com.example.administrator.view_test.H5;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.view_test.H5.webview.SnapJsInterface;
import com.example.administrator.view_test.R;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class H5ToAndroidActivity2 extends AppCompatActivity {

    private WebView webView;

    private boolean mWebViewError = false;

    public static final String WEB_BRIDGE = "WebSnapBridge";

    String mUrl = "www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_to_android2);
        initView();

        initData();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            webSettings.setDisplayZoomControls(true);
        }
        webSettings.setDomStorageEnabled(true);

//        SnapWebViewDialog snapWebViewDialog = new SnapWebViewDialog();
//        snapWebViewDialog.setWebView(mWebView);
        webView.addJavascriptInterface(new SnapJsInterface(this, webView), WEB_BRIDGE);
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                showLoading();
                Log.e("正在加载页面中", "");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mWebViewError = true;
                webView.setVisibility(View.GONE);
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                if (webView.canGoBack()) {
//                    mTitleBar.showLeftLayout();
                } else {
//                    mTitleBar.hideLeftLayout();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                hideLoading();
                Log.e("加载页面完成", "");
                if (mWebViewError) {//加载失败
                    webView.setVisibility(View.GONE);
//                    mNoDataTip.doTipsView("点击刷新");
                    mWebViewError = false;
                } else {
                    webView.setVisibility(View.VISIBLE);
//                    mNoDataTip.setVisibility(View.GONE);
                }
            }

//            @Override
//            public void onReceivedSslError(WebView view,
//                                           SslErrorHandler handler, SslError error) {
//                handler.proceed();
//            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public void initData() {
        webView.loadUrl(mUrl);
    }

}
