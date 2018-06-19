package com.example.administrator.view_test.RecyclerMoreActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class RecyclerViewMoreActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.add)
    Button add;

    private RecyclerMoreAdapter recyclerMoreAdapter;

    private List<String> data1 = new ArrayList<>();

    private List<String> data2 = new ArrayList<>();

    private List<String> data3 = new ArrayList<>();

    private List<MyBean> myBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        recyclerMoreAdapter = new RecyclerMoreAdapter(this, myBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerMoreAdapter);
    }

    private void initData() {
        MyBean myBean = new MyBean("数量大于等于3的第一个", 2);
        myBeanList.add(myBean);
        myBean = new MyBean("数量大于等于3的第二个", 4);
        myBeanList.add(myBean);
        myBean = new MyBean("数量大于等于3的第三个", 3);
        myBeanList.add(myBean);

        myBean = new MyBean("数量等于1的第1个", 1);
        myBeanList.add(myBean);

        myBean = new MyBean("数量等于2的第一个", 2);
        myBeanList.add(myBean);
        myBean = new MyBean("数量等于2的第二个", 3);
        myBeanList.add(myBean);

        myBean = new MyBean("数量等于2的第一个", 2);
        myBeanList.add(myBean);
        myBean = new MyBean("数量等于2的第二个", 4);
        myBeanList.add(myBean);
        myBean = new MyBean("数量等于2的第一个", 4);
        myBeanList.add(myBean);
        myBean = new MyBean("数量等于2的第二个", 4);
        myBeanList.add(myBean);
        myBean = new MyBean("数量等于2的第一个", 4);
        myBeanList.add(myBean);
        myBean = new MyBean("数量等于2的第二个", 3);
        myBeanList.add(myBean);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler_more;
    }

    @Override
    public void initPresenter() {

    }

    public void add() {
//        recyclerMoreAdapter.addData(data2);
    }

    public class MyBean {

        public MyBean(String content, int type) {
            this.content = content;
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        private String content;
        private int type;
    }

}
