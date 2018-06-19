package com.example.administrator.view_test.H5;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class H5ToAndroidActivity1 extends AppCompatActivity {

    private WebView webView;
    private webAppInterface webAppInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_to_android);
        initWebView();
    }

    private void initWebView() {

        webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        // 告诉WebView启用JavaScript执行。默认的是false。
        webSettings.setJavaScriptEnabled(true);
        //  页面加载好以后，再放开图片
        webSettings.setBlockNetworkImage(false);
        // 启动应用缓存
        webSettings.setAppCacheEnabled(true);


        loadingLocalHtml();
    }

    private void loadingLocalHtml() {
//        String tpl = getFromAssets("share.html");
//        webView.loadDataWithBaseURL(null, tpl, "text/html", "utf-8", null);
//        webView.loadUrl("file:///android_asset/share.html");
        webView.loadUrl("http://112.124.22.238:8081/course_api/wares/detail.html");
        webView.addJavascriptInterface(webAppInterface, "appInterface");
        webView.setWebViewClient(new MyWebViewClient());
    }

    /*
    * 获取html文件
    */
    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void loadingRemoteHtml(String url) {
        webAppInterface = new webAppInterface(this);
        webView.loadUrl(url);
        webView.addJavascriptInterface(webAppInterface, "lvlv");
        webView.setWebViewClient(new MyWebViewClient());
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

//            if (mSpotsDialog != null && mSpotsDialog.isShowing())
//                mSpotsDialog.dismiss();
//            webAppInterface.showDetail();
        }
    }

    class webAppInterface {

        private Context mContext;

        public webAppInterface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void showDetail() {

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    webView.loadUrl("javascript:showDetail(" + hotWareBean.getId() + ")");
//                }
//            });
        }

        @JavascriptInterface
        public void buy(long id) {

        }

        //参数需要一致
        @JavascriptInterface
        public void addToCart(long id) {
            Log.d(MyApplication.H5_DEBUG, "id");
        }

//        @JavascriptInterface
//        public void share(String title, String content, String url, String imgUrl){
//            Log.d(MyApplication.H5_DEBUG, "title" + title + "content" + content + "url" + url + "imgUrl" + imgUrl);
//        }
    }
}
