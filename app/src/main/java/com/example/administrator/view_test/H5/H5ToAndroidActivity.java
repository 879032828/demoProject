package com.example.administrator.view_test.H5;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Button;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;

public class H5ToAndroidActivity extends BaseActivity {

    private WebView webView;
    private webAppInterface webAppInterface;

    @BindView(R.id.button)
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initWebView();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_h5_to_android;
    }

    @Override
    public void initPresenter() {

    }

    private void initView(){
        webView = (WebView) findViewById(R.id.webview);

    }

    private void initWebView(){

        WebSettings webSettings = webView.getSettings();
        // 告诉WebView启用JavaScript执行。默认的是false。
        webSettings.setJavaScriptEnabled(true);
        //  页面加载好以后，再放开图片
        webSettings.setBlockNetworkImage(false);
        // 启动应用缓存
        webSettings.setAppCacheEnabled(true);
        webAppInterface = new webAppInterface(this);

        loadingRemoteUrl("http://192.168.1.102:8080/");
//        loadingRemoteUrl("http://www.baidu.com");
//        loadingRemoteUrl("http://10.24.1.214/shenyang");
//        loadingLocalUrl();
    }

    private void loadingLocalUrl(){
        webView.loadUrl("file:///android_asset/share.html");
        webView.addJavascriptInterface(webAppInterface, "lvlv");
        webView.setWebViewClient(new MyWebViewClient());
    }

    private void loadingRemoteUrl(String url){
        webView.loadUrl(url);
        webView.setWebViewClient(new MyWebViewClient());
    }

    class webAppInterface{

        private Context mContext;
        public webAppInterface(Context context) {
            mContext = context;
        }

        @JavascriptInterface
        public void showDetail(){

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:showDetail(" + 1 + ")");
                }
            });
        }

        @JavascriptInterface
        public void buy(long id){

        }

        //参数需要一致
        @JavascriptInterface
        public void addToCart(long id){
            Log.d(MyApplication.H5_DEBUG, "id");
        }

        @JavascriptInterface
        public void share(String title, String content, String url, String imgUrl){
            Log.d(MyApplication.H5_DEBUG, "share" + title + content + url + imgUrl);
        }
    }


    class  MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            webAppInterface.showDetail();
        }
    }

    @OnClick(R.id.button)
    public void getUrl(){
        Log.d(MyApplication.H5_DEBUG, webView.getUrl() + "");
    }

}
