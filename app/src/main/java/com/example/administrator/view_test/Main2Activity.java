package com.example.administrator.view_test;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.LenTextView.LeanTextViewActivity;
import com.example.administrator.view_test.ScrollView.Scroller.ScrollerActivity;
import com.example.administrator.view_test.ValueAnimator.ValueAnimatorActivity;
import com.example.administrator.view_test.baiduSDK.MyLocationListener;
import com.example.administrator.view_test.doubleClick.doubleClickActivity;
import com.example.administrator.view_test.privateDir.privatedirActivity;
import com.example.administrator.view_test.progressView.ProgressViewActivity;
import com.example.administrator.view_test.splash.splashActivity;
import com.example.administrator.view_test.vlayout.vlayoutActivity;
import com.example.administrator.view_test.webp.webpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {

    @BindView(R.id.showScroller)
    public Button showScroller;


    @BindView(R.id.showsplash)
    public Button showsplash;

    @BindView(R.id.showprivate)
    public Button showprivate;

    @BindView(R.id.showVlayout)
    public Button showVlayout;

    @BindView(R.id.showValueAnimation)
    public Button showValueAnimation;

    @BindView(R.id.showDoubleClick)
    public Button showDoubleClick;

    @BindView(R.id.text)
    public TextView text;

    @BindView(R.id.animate_vector)
    public ImageView animate_vector;

    @BindView(R.id.showProgressView)
    public Button showProgressView;

    @BindView(R.id.showWebp)
    public Button showWebp;

    @BindView(R.id.showLean)
    public Button showLean;

    public LocationClient mLocationClient = null;

    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //百度地图初始化
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        initLocation();

        mLocationClient.start();

        text.setText("hahahahah\nhahahaha\nhahaha");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewgroup_test;
    }

    @Override
    public void initPresenter() {

    }


    @OnClick(R.id.showScroller)
    public void gotoshowScroller() {
        startActivity(new Intent(Main2Activity.this, ScrollerActivity.class));
    }

    @OnClick(R.id.showsplash)
    public void gotosplashActivity() {
        startActivity(new Intent(Main2Activity.this, splashActivity.class));
    }

    @OnClick(R.id.showprivate)
    public void showprivate() {
        startActivity(new Intent(Main2Activity.this, privatedirActivity.class));
    }

    @OnClick(R.id.showVlayout)
    public void showVlayout() {
        Intent intent = new Intent(Main2Activity.this, vlayoutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.showValueAnimation)
    public void showValue() {
        Intent intent = new Intent(Main2Activity.this, ValueAnimatorActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.showDoubleClick)
    public void showDoubleClick() {
        Intent intent = new Intent(Main2Activity.this, doubleClickActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.animate_vector)
    public void startAnimation() {
        Drawable drawable = animate_vector.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    @OnClick(R.id.showProgressView)
    public void showProgressView() {
        Intent intent = new Intent(Main2Activity.this, ProgressViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.showWebp)
    public void showWebp() {
        Intent intent = new Intent(Main2Activity.this, webpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.showLean)
    public void showLean() {
        Intent intent = new Intent(Main2Activity.this, LeanTextViewActivity.class);
        startActivity(intent);
    }
}
