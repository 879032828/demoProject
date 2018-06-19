package com.example.administrator.view_test.IRecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;


public class IRecyclerActivity extends BaseActivity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_irecycler;
    }

    @Override
    public void initPresenter() {

    }

    public void initView(){


//        irc.setLayoutManager(new LinearLayoutManager(this));
    }

}