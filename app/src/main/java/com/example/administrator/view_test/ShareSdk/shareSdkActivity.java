package com.example.administrator.view_test.ShareSdk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.ShareSdk.login.LoginApi;
import com.example.administrator.view_test.ShareSdk.login.OnLoginListener;
import com.example.administrator.view_test.ShareSdk.login.UserInfo;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class shareSdkActivity extends BaseActivity {

    @BindView(R.id.wechat)
    public Button wechat;

    @BindView(R.id.QQ)
    public Button QQbtn;

    @BindView(R.id.sina)
    public Button sina;

    private Platform platform;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sharesdk;
    }

    @Override
    public void initPresenter() {

    }

    /* 获取平台列表,显示平台按钮*/
    private void initPlatformList() {

    }

    @OnClick(R.id.wechat)
    public void wechat(){
        ShareSDK.initSDK(this);
        platform = ShareSDK.getPlatform(SinaWeibo.NAME);
        name = platform.getName();
        if (platform.isAuthValid()) {
            platform.removeAccount(true);
        }

        login(name);
    }

    @OnClick(R.id.QQ)
    public void QQ(){

        ShareSDK.initSDK(this);
        platform = ShareSDK.getPlatform(QQ.NAME);
        name = platform.getName();
        if (platform.isAuthValid()) {
            platform.removeAccount(true);
        }

        login(name);
    }

    @OnClick(R.id.sina)
    public void sina(){

    }

    /*
 * 演示执行第三方登录/注册的方法
 * <p>
 * 这不是一个完整的示例代码，需要根据您项目的业务需求，改写登录/注册回调函数
 *
 * @param platformName 执行登录/注册的平台名称，如：SinaWeibo.NAME
 */
    private void login(String platformName) {
        LoginApi api = new LoginApi();
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(String platform, HashMap<String, Object> res) {
                // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                // 此处全部给回需要注册
                return true;
            }

            public boolean onRegister(UserInfo info) {
                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                return true;
            }
        });
        api.login(this);
    }
}
