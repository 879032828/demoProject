package com.example.administrator.view_test.TabLayout;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/5/17 0017.
 * fragment懒加载
 */

public abstract class LazyLoadBaseFragment extends BaseFragment {

    protected static final String TAG = "lazy";

    //只有子类Fragment可见，且已经初始化成功后，才允许调用lazyload()进行数据的加载
    protected boolean isVisible;//标志位，标志当前fragment是否可见
    protected boolean isPrepared;// 标志位，标志已经初始化完成。

    protected Gson mGson = new Gson();

    /**
     * 获取当前Fragment是否可见
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()){
            isVisible = true;
            onVisible();//当fragment可见时，回调该方法
        }else {
            isVisible = false;
            onInVisible();
        }
    }

    protected void onVisible(){
        lazyload();
    }

    protected void onInVisible(){

    }

    /**
     * 在子类中实现该方法，只有当切换到当前fragment，才进行数据的加载
     * 实现fragment的懒加载
     */
    protected abstract void lazyload();
}
