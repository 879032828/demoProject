package com.example.administrator.view_test.LenTextView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.listview.listAdapter;
import com.example.administrator.view_test.listview.pullToRefreshView.RefreshableView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class LeanTextViewActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_leantextview;
    }

    @Override
    public void initPresenter() {

    }
}
