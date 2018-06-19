package com.example.administrator.view_test.listview.pullToRefreshListViewGai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.listview.listAdapter;
import com.example.administrator.view_test.listview.pullToRefreshListViewGai.RecyclerViewAdapter.RecycAdapter;
import com.example.administrator.view_test.listview.pullToRefreshView.RefreshableView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class listview2Activity extends BaseActivity {

    @BindView(R.id.floatingActionButton)
    public FloatingActionButton floatingActionButton;

    private listAdapter mProjectListAdapter;
    private RecycAdapter recycAdapter;
    private FloatingActionButton mFloatingActionButton;

    private PullToRefreshListViewGai pullToRefreshListView;
    private View lvFavorite_footer;
    private ProgressBar lvFavorite_foot_progress;
    private TextView lvFavorite_foot_more;

    private View emptyView;
    private TextView emptyTxt;
    private Button LoadingAgainView;


    private int mPage = 1;
    private int mType = 0;

    private List<String> data = new ArrayList<>();

    /**
     * <p>Discription:[是否加载更多标记]</p>
     */
    private boolean ableToPull = true;
    /**
     * <p>Discription:[前一次请求结束标记]</p>
     */
    private boolean isLoadingData = false;

    /**
     * <p>Discription:[判断是否可以下拉刷新]</p>
     */
    private boolean ableToRefresh = false;

    private ListView listView;

    private RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_listview2;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {

        lvFavorite_footer = getLayoutInflater().inflate(R.layout.listview_footer2, null);
        lvFavorite_foot_progress = (ProgressBar) lvFavorite_footer.findViewById(R.id.listview_foot_progress);
        lvFavorite_foot_more = (TextView) lvFavorite_footer.findViewById(R.id.listview_foot_more);
        lvFavorite_footer.setVisibility(View.GONE);

        listView = (ListView) findViewById(R.id.list3);
        recyclerView = (RecyclerView) findViewById(R.id.list4);

        pullToRefreshListView = (PullToRefreshListViewGai) findViewById(R.id.list2);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshListViewGai.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData(3);
            }
        });

        pullToRefreshListView.addFooterView(lvFavorite_footer);
        pullToRefreshListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        pullToRefreshListView.setOnScrollListener(new AbsListView.OnScrollListener() { //上拉加载

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //每次往上拉的的时候都会调用这个方法，判断该是否滑动到列表底部，如果是列表底部就作网络请求
                pullToRefreshListView.onScrollStateChanged(view, scrollState);
                if (data.size() == 0) {
                    return;
                }

                //判断是否滚动到底部,如果是底部，就作网络请求
                boolean scrollEnd = false;
                try {
                    if (view.getPositionForView(lvFavorite_footer) == view.getLastVisiblePosition())
                        scrollEnd = true;
                } catch (Exception e) {
                    scrollEnd = false;
                }

                if (scrollEnd && ableToPull) {
//                    lvFavorite_foot_more.setText(R.string.load_ing);
                    if (!isLoadingData) {
                        requestData(2);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                pullToRefreshListView.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }

        });

        mProjectListAdapter = new listAdapter(this, data);
        listView.setAdapter(mProjectListAdapter);
        pullToRefreshListView.setAdapter(mProjectListAdapter);

        recycAdapter = new RecycAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycAdapter);
    }

    private void initData(){
        for (int i = 0; i < 15; i++){
            data.add(i + "");
        }
    }

    private void requestData(final int type) {

        isLoadingData = true;
        if (type == 2) { //加载
            lvFavorite_footer.setVisibility(View.VISIBLE);
            ableToPull = false;
        } else if (type == 3) {
            mPage = 1;
            isLoadingData = false;
        }

//        RequestParams params = new RequestParams();
//        params.put("type", mType);
//        params.put("page", mPage);
//        Log.e("项目列表参数：", params.toString());
//        Log.e("项目列表url：", ProjectList1Activity.ProjectListUrl);
//        SnapHttpClient.postDirect(ProjectList1Activity.ProjectListUrl, params, new JsonHttpResponseHandler() {
//            @Override
//            public void onStart() {
//                super.onStart();
//                showLoading();
////                setEmptyView(0);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, JSONObject response) {
//                super.onSuccess(response);
//                Log.e("请求返回数据：", response.toString());
//
//                JSONObject obj = null;
//                try {
//                    obj = new JSONObject(response.toString());
//                    String status = obj.getString("code");
//                    Log.e("code： ", status);
//                    JSONArray array = obj.getJSONArray("data");
//                    if (PingAnUtils.isNetSuccess(status)) {
//                        if (array.length() == 0) { //没有查询到数据
//                            //首次请求数据，当没有数据
//                            if (type == 1 || type == 4) {
//                                //首次请求数据
//                                mProjectListAdapter = new ProjectListAdapter(getActivity(), data);
//                                pullToRefreshListView.setAdapter(mProjectListAdapter);
//                                setEmptyView(1);
//                            } else {
//                                //不是第一次请求数据，但当前请求没有数据
//                                //即已加载所有数据
//                                lvFavorite_footer.setVisibility(View.VISIBLE);
//                                lvFavorite_foot_progress.setVisibility(View.GONE);
//                                lvFavorite_foot_more.setText(getResources().getString(R.string.already_load_data));
//                                ableToPull = true;
//                                pullToRefreshListView.onRefreshComplete();
//                            }
//                            isLoadingData = false;
//                            return;
//                        } else {
//                            Log.e("数据返回成功 ", "数据返回成功");
//                            projectInfoBean projectInfoBean = mGson.fromJson(response.toString(), com.neusoft.snap.pingan.project.bean.ProjectListBean.projectInfoBean.class);
//                            if (type == 3){
//                                //当数据不为空且是下拉刷新时
//                                //先将当前数据清空，再把数据添加
//                                data.clear();
//                            }
//                            if (data == null){
//                                data = new ArrayList<projectInfo>();
//                            }
//                            if (projectInfoBean.getData() == null){
//                                Toast.makeText(getActivity(), "项目信息为空", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                            data.addAll(projectInfoBean.getData());
//                            if (type == 1 || type == 4) {
//                                //首次加载数据，且数据不为空
//                                mProjectListAdapter = new ProjectListAdapter(getActivity(), data);
//                                mProjectListAdapter.setOnItemClickListener(new projectItemClickListener());
//                                pullToRefreshListView.setAdapter(mProjectListAdapter);
//                                mPage++;
//                            } else if (type == 2) { //加载下一页
//                                mPage++;
//                                mProjectListAdapter.notifyDataSetChanged();
//                                lvFavorite_footer.setVisibility(View.GONE);
//                            } else if (type == 3) {
//                                mProjectListAdapter = null;
//                                mProjectListAdapter = new ProjectListAdapter(getActivity(), data);
//                                mProjectListAdapter.setOnItemClickListener(new projectItemClickListener());
//                                pullToRefreshListView.setAdapter(mProjectListAdapter);
//                                pullToRefreshListView.onRefreshComplete();
//                                mPage++;
//                            }
//                            ableToPull = true;
//                            pullToRefreshListView.onRefreshComplete();
//                            isLoadingData = false;
//                        }
//                    } else { //没有请求到数据
//                        hideLoading();
//                        Toast.makeText(getActivity(), "获取项目数据出错,后台正在努力中", Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    hideLoading();
//                    Log.e("json解析错误", "json解析错误");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//                Log.d(TAG, "错误信息 : " + error.toString());
//                hideLoading();
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                hideLoading();
//            }
//        });

    }

    private void setEmptyView(int type) { //设置空布局,type为0时表示正在请求，为1时表示请求完毕

        if (type == 0) {
            emptyTxt.setText("正在获取项目数据");
        } else if (type == 1) {
            emptyTxt.setText("没有查询到项目数据");
            LoadingAgainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestData(3);
                }
            });
        }
        pullToRefreshListView.setEmptyView(emptyView);
    }

    public void setType(int type){
        this.mType = type;
    }

    public void requestDataAgain(){
        requestData(3);
    }
}
