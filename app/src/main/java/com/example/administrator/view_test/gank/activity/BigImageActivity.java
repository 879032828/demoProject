package com.example.administrator.view_test.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.gank.bean.PhotoGirl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class BigImageActivity extends BaseActivity implements ViewPager.OnPageChangeListener, PhotoViewAttacher.OnPhotoTapListener{

    @BindView(R.id.viewpager)
    public ViewPager mViewPager;

    @BindView(R.id.view_page)
    public TextView mViewPage;

    private int mPosition;
    private ArrayList<PhotoGirl> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager.setCurrentItem(mPosition);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_big_image;
    }

    @Override
    public void initPresenter() {

    }

    private void getIntentData(){
        Bundle bundle = getIntent().getBundleExtra("photo");
        mPosition = bundle.getInt("position");
        mList = bundle.getParcelableArrayList("list");
        mViewPage.setText((mPosition + 1) + "/" + mList.size());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(MyApplication.PAGER_DEBUG, "onPageSelected position = " + position);
        position = position + 1;
        mViewPage.setText(position + "/" + mList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
        finish();
    }

    class MyViewPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater inflater;

        public MyViewPagerAdapter(Context context) {
            mContext =  context;
            this.inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            if (mList == null || mList.size() == 0) {
                return 0;
            }
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.item_image, container, false);
            PhotoView photoImage = (PhotoView) view.findViewById(R.id.photo_image);
            photoImage.setOnPhotoTapListener(BigImageActivity.this);
//            photoImage.setZoomable(false);//设置图片是否缩放
//            photoImage.setMaximumScale(20f);//设置放大的倍数
            String adapter_image_Entity = (String) getItem(position);
            Glide.with(mContext)
                    .load(adapter_image_Entity)
                    .into(photoImage);
            container.addView(view, 0);
            return view;
        }

        Object getItem(int position) {
            return mList.get(position).getUrl();
        }
    }
}
