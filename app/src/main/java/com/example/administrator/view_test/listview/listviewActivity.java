package com.example.administrator.view_test.listview;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.Popupwindow.MyPopupWindow;
import com.example.administrator.view_test.Popupwindow.popupwindowAdapter;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.listview.pullToRefreshView.RefreshableView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class listviewActivity extends BaseActivity {

    @BindView(R.id.list_view)
    public ListView listview;

    public List<String> liststr = new ArrayList<>();

    private listAdapter listAdapter;

    RefreshableView refreshableView;

    String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" , "M", "N", "O", "P", "Q", "R"};

    MyHandler myHandler = new MyHandler();

    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            liststr.add("haha");
            listAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
        refreshableView.setOnLoadingMoreListener(new RefreshableView.PullUpToLoadingMoreListener() {
            @Override
            public void onLoadingMore() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myHandler.sendEmptyMessage(0);
            }
        });
        listview = (ListView) findViewById(R.id.list_view);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listAdapter = new listAdapter(this, liststr);
        listview.setAdapter(listAdapter);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);


    }

    private void initData(){
        for (int i = 0; i < 15; i++){
            liststr.add(i + "");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_listview;
    }

    @Override
    public void initPresenter() {

    }
}
