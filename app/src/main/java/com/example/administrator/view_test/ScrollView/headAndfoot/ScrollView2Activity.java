package com.example.administrator.view_test.ScrollView.headAndfoot;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScrollView2Activity extends BaseActivity {

    @BindView(R.id.recycler_view2)
    RecyclerView mRecyclerView;

    private HeaderAndFooterWrapper headerAndFooterWrapper;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        headerAndFooterWrapper = new HeaderAndFooterWrapper();
        mRecyclerView.setAdapter(headerAndFooterWrapper);

        String[] titles = getResources().getStringArray(R.array.titles);
        List<String> data  = new ArrayList<>();
        for (int i = 0; i < titles.length; i++){
            data.add(titles[i]);
        }

        headerAndFooterWrapper.addDatas(data);
        setHeader(mRecyclerView);
        headerAndFooterWrapper.setOnItemClickListener(new HeaderAndFooterWrapper.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(ScrollView2Activity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.activity_head, view, false);
        headerAndFooterWrapper.setHeaderView(header);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scroll2;
    }

    @Override
    public void initPresenter() {

    }
}
