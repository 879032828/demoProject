package com.example.administrator.view_test.splash;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.tencent.smtt.sdk.WebView;

/**
 * Describe:js回调基础类
 * Author: jidp
 * Date: 2017/4/1
 */
public class SnapJsInterface {

    private static final String JS_METHOD_PRE = "javascript:";
    private static final String JS_LOGIN_CALLBACK_METHOD = "snap_jsdk_login_callback";
    private static final String JS_SHARE_CALLBACK_METHOD = "snap_jsdk_share_callback";
    private static final String JS_TOKEN_CALLBACK_METHOD = "snap_jsdk_getToken_callback";
    private static final String JS_RETURN_HOME_CALLBACK_METHOD = "snap_jsdk_retuenHome_callback";
    private static final String JS_SEND_TOKEN_METHOD = "snap_jsdk_sendToken";

    private Gson mGson;
    private Context mCtx;
    private WebView mWebView;

    public SnapJsInterface(Context ctx, WebView webView) {
        mCtx = ctx;
        mWebView = webView;
        mGson = new Gson();
    }

    @JavascriptInterface
    public void gotoDownload(String param) {
//        if (!TextUtils.isEmpty(param)) {
//            AttachDownloadVO attachDownloadVO = mGson.fromJson(param, AttachDownloadVO.class);
//            if (attachDownloadVO != null) {
//                // TODO jidp 在这下载
//            }
//        }
    }

    /**
     * 登录
     */
    @JavascriptInterface
    public void snap_jsdk_login(String param) {
        callJsMethod(JS_LOGIN_CALLBACK_METHOD, "");
    }

    /**
     * 分享
     */
    @JavascriptInterface
    public void snap_jsdk_share(String param) {
//        if (TextUtils.isEmpty(param)) {
//            return;
//        }
//        JSShareResponse jsShareResponse = mGson.fromJson(param, JSShareResponse.class);
//        if (jsShareResponse == null) {
//            return;
//        }
//        ReceivedMessageBodyBean bodyBean = new ReceivedMessageBodyBean();
//        ReceivedMessageBaseBean baseBean = new ReceivedMessageBaseBean();
//        ReceivedMessageFileBean fileBean = new ReceivedMessageFileBean();
//
//        fileBean.setFrom(MsgBodyType.SHARE_ARTICLE);
//        fileBean.setContent(MyJsonUtils.toJsonStr(makeOfficialArticle(jsShareResponse)));
//
//        baseBean.setFmfb(fileBean);
//        bodyBean.setMessage(baseBean);
//        ContactUtils.goToSelectForwardMembers(mCtx, null, null, bodyBean);
//
//        callJsMethod(JS_SHARE_CALLBACK_METHOD, "");
    }

//    private OfficialAccountsMsgVO makeOfficialArticle(JSShareResponse jsShareResponse) {
//        OfficialAccountsMsgVO offcialAccount = new OfficialAccountsMsgVO();
//
//        offcialAccount.setBrief(jsShareResponse.getBrief());
//        offcialAccount.setAvatar(jsShareResponse.getAvatar());
//        offcialAccount.setUrl(jsShareResponse.getUrl());
//        offcialAccount.setTime(Long.parseLong(jsShareResponse.getTime()));
//        offcialAccount.setArticleId(jsShareResponse.getArticleId());
//        offcialAccount.setTitle(jsShareResponse.getTitle());
//        offcialAccount.setType(jsShareResponse.getType());
//
//        return offcialAccount;
//    }

    /**
     * 获取token
     */
    @JavascriptInterface
    public void snap_jsdk_getToken(String param) {
        callJsMethod(JS_TOKEN_CALLBACK_METHOD, "");
//        if (ImHelper.isLogin()) {
//            TokenUtils.getMicroInfoToken(new TokenUtils.OnGetTokenListener() {
//                @Override
//                public void onGetTokenSuccess(String token) {
//                    JsonObject jsonObject = new JsonObject();
//                    jsonObject.addProperty("token", token);
//                    callJsMethod(JS_SEND_TOKEN_METHOD, jsonObject.toString());
//                }
//
//                @Override
//                public void onGetTokenFailed(String errMsg) {
//                    JsonObject jsonObject = new JsonObject();
//                    jsonObject.addProperty("token", "");
//                    callJsMethod(JS_SEND_TOKEN_METHOD, jsonObject.toString());
//                }
//            });
//        } else {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("token", "");
//            callJsMethod(JS_SEND_TOKEN_METHOD, jsonObject.toString());
//        }
    }


    /**
     * 返回首页
     */
    @JavascriptInterface
    public void snap_jsdk_retuenHome(final String param) {
        Log.e("snap_jsdk_retuenHome", param + "");
        callJsMethod(JS_RETURN_HOME_CALLBACK_METHOD, "");

//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
//                UIEvent event = new UIEvent();
//                event.setType(UIEventType.H5ReturnHome);
//                event.putData(Constant.H5_RETURN_HOME,param);
//                UIEventManager.getInstance().broadcast(event);
//            }
//        });

    }

    /**
     * android调用js
     *
     * @param methodName js方法名
     * @param param      传给web端的参数
     */
    public void callJsMethod(final String methodName, final String param) {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                //mWebView.loadUrl(JS_METHOD_PRE + methodName + "(" + param + ")");
                String JAVASCRIPT_URL = JS_METHOD_PRE + methodName + "(" + param + ")";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 对于系统19及以上使用evaluateJavascript来做异步js注入
                    mWebView.evaluateJavascript(JAVASCRIPT_URL.substring(JS_METHOD_PRE.length()), null);
                } else { // 其余的直接用loadUrl的方式处理
                    mWebView.loadUrl(JAVASCRIPT_URL);
                }
            }
        });
    }
}
