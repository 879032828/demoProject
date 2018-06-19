package com.example.administrator.view_test.gank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.event.UIEvent;
import com.example.administrator.view_test.event.UIEventHandler;
import com.example.administrator.view_test.event.UIEventType;
import com.example.administrator.view_test.gank.Model.PhotoDataModel;
import com.example.administrator.view_test.gank.ScrollAwareFABBehavior1;
import com.example.administrator.view_test.gank.bean.PhotoGirl;
import com.example.administrator.view_test.gank.contract.WelfareContract;
import com.example.administrator.view_test.gank.presenter.PhotoDataPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WelfareActivity extends BaseActivity<PhotoDataPresenter, PhotoDataModel> implements WelfareContract.View, XRecyclerView.LoadingListener{

    @BindView(R.id.showRetrofit)
    public Button mShowRetrofit;

    @BindView(R.id.photo_list_x)
    public XRecyclerView mXRecyclerView;

    @BindView(R.id.floatingActionButton)
    public FloatingActionButton floatingActionButton;

    private photoDataAdapter adapter;
    private int size = 10;
    private int page = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    public void initView() {
        adapter = new photoDataAdapter(this);
        adapter.setOnItemClickListener(new photoDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) adapter.getDatas());

                Intent intent = new Intent(WelfareActivity.this, BigImageActivity.class);
                intent.putExtra("photo", bundle);
                startActivity(intent);
            }
        });
        mXRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mXRecyclerView.setLoadingListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welfare;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @OnClick(R.id.showRetrofit)
    public void requestData() {
        mPresenter.requestPhotoData(size, page);
    }

    @Override
    public void showPhoto(List<PhotoGirl> list) {
        adapter.setDatas(list);
        mXRecyclerView.setAdapter(adapter);
        Toast.makeText(WelfareActivity.this, "已获取到数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshPhoto(List<PhotoGirl> list) {
        if (adapter != null){
            adapter.setDatas(list);
            mXRecyclerView.refreshComplete();
        }
    }

    @Override
    public void loadingMorePhoto(List<PhotoGirl> list) {
        if (adapter != null){
            adapter.addData(list);
            mXRecyclerView.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        mPresenter.refreshPhotoData(size, page);
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.loadingMorePhotoData(size, page);
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(MyApplication.FLOATING_DEBUG, "handleMessage");
            ScrollAwareFABBehavior1.animateIn(floatingActionButton);
        }
    }

    @UIEventHandler(UIEventType.ClearChatLog)
    public void showHandler(UIEvent uiEvent){

        MyHandler myHandler = new MyHandler();
        myHandler.sendEmptyMessage(0);
    }
}
