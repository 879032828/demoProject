package com.example.administrator.view_test.Base;

import android.content.Context;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public abstract class BasePresenter<T, E> {

    public Context mContext;
    public T mView;
    public E mModel;

    public void setVM(T view, E model){
        this.mView = view;
        this.mModel = model;
    }
}
