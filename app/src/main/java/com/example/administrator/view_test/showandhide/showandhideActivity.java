package com.example.administrator.view_test.showandhide;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class showandhideActivity extends BaseActivity {

    @BindView(R.id.showMore)
    public Button showbutton;

    @BindView(R.id.hide)
    public Button hidebutton;


    @BindView(R.id.showlayout)
    public LinearLayout mShowLayout;

    @BindView(R.id.four)
    public TextView fourview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_showandhide;
    }

    @Override
    public void initPresenter() {

    }

    @OnClick(R.id.showMore)
    public void showMore(){
        mShowLayout.setVisibility(View.VISIBLE);
        fourview.setText("0000");
    }

    @OnClick(R.id.hide)
    public void hide(){
        mShowLayout.setVisibility(View.GONE);
    }
}
