package com.example.administrator.view_test.Base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;

import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.event.UIEventDispatcher;
import com.example.administrator.view_test.event.UIEventManager;
import com.example.administrator.view_test.util.TUtil;
import com.example.common.baseapp.AppManager;
import com.example.common.commonwidget.StatusBarCompat;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {

    public UIEventDispatcher uiEventDispatcher = null;
    public T mPresenter;
    public E mModel;

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        doBeforeSetcontentView();
        setContentView(getLayoutId());
        doAfterSetcontentView();

        ButterKnife.bind(this);

        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initPresenter();

        registerEvent();
    }

    private void registerEvent() {
        Log.d(MyApplication.EVENT_DEBUG, UIEventManager.getInstance().canHandleUIEvent(this.getClass()) + "");
        //注册事件监听
        if (UIEventManager.getInstance().canHandleUIEvent(this.getClass())) {
            uiEventDispatcher = new UIEventDispatcher(this);
            UIEventManager.getInstance().register(uiEventDispatcher.getEventTypes(), uiEventDispatcher);
        }
    }

    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();
    }

    private void doAfterSetcontentView() {
        //设置根布局的FitsSystemWindows
        SetFitsSystemWindows();
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    /**
     * FitsSystemWindows的作用：
     * 当status bar为透明或半透明时(4.4以上),系统会设置view的paddingTop值为一个适合的值(status bar的高度)
     * 让view的内容不被上拉到状态栏，当在不占据status bar的情况下(4.4以下)会设置paddingTop值为0(因为没有占据status bar所以不用留出空间)。
     * <p>
     * 设置根布局的FitsSystemWindows
     */
    protected void SetFitsSystemWindows() {
        ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);
    }


    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

//    //初始化view
//    public abstract void initView();


    @Override
    protected void onDestroy() {


        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }
}
