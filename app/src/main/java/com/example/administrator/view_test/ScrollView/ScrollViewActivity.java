package com.example.administrator.view_test.ScrollView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

public class ScrollViewActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private AnimAdapter animAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于list view V23.2.0 支持自动计算高度大小，也就是可以嵌套了

        animAdapter = new AnimAdapter(this);
        String[] titles = getResources().getStringArray(R.array.titles);
        animAdapter.addTitles(titles);
        mRecyclerView.setAdapter(animAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scroll;
    }

    @Override
    public void initPresenter() {

    }
}
