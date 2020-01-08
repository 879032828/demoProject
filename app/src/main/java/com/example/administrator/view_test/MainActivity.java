package com.example.administrator.view_test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.CallPhone.callphoneActivity;
import com.example.administrator.view_test.H5.H5ToAndroidActivity;
import com.example.administrator.view_test.H5.h5Activity;
import com.example.administrator.view_test.LongImage.LongImageActivity;
import com.example.administrator.view_test.Popupwindow.popupwindowActivity;
import com.example.administrator.view_test.PropertyAnimation.AnimationActivity;
import com.example.administrator.view_test.RecyclerMoreActivity.RecyclerViewMoreActivity;
import com.example.administrator.view_test.RxJava.rxjava1Activity;
import com.example.administrator.view_test.RxJava.rxjava2Activity;
import com.example.administrator.view_test.RxJava.rxjava2AntiShakeActivity;
import com.example.administrator.view_test.ScrollView.headAndfoot.ScrollView2Activity;
import com.example.administrator.view_test.Slideshow.SlideShowActivity;
import com.example.administrator.view_test.TabLayout.tabLayoutActivity;
import com.example.administrator.view_test.ViewPager.viewpagerActivity;
import com.example.administrator.view_test.ViewPager.viewpagerFragment.viewpagerfragmentActivity;
import com.example.administrator.view_test.customview.ScrollView.ScrollView1Activity;
import com.example.administrator.view_test.customview.customActivity;
import com.example.administrator.view_test.event.EventTestActivity;
import com.example.administrator.view_test.event.UIEvent;
import com.example.administrator.view_test.event.UIEventHandler;
import com.example.administrator.view_test.event.UIEventType;
import com.example.administrator.view_test.gank.activity.WelfareActivity;
import com.example.administrator.view_test.hellocharts.HelloChartsActivity;
import com.example.administrator.view_test.listview.listviewActivity;
import com.example.administrator.view_test.listview.pullToRefreshListViewGai.listview2Activity;
import com.example.administrator.view_test.photodemo.photoActivity;
import com.example.administrator.view_test.showandhide.showandhideActivity;
import com.example.administrator.view_test.websocket.okhttpActivity;
import com.example.administrator.view_test.websocket.websocketActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button1)
    public Button button1;

    @BindView(R.id.button2)
    public Button button2;

    @BindView(R.id.request_movie)
    public Button mRequestMovie;

    @BindView(R.id.show_and_hide)
    public Button mShowAndHide;

    @BindView(R.id.call_phone)
    public Button mCallPhone;

    @BindView(R.id.show_constraint)
    public Button mShowConstraint;

    @BindView(R.id.show_viewpager)
    public Button mShowViewpager;

    @BindView(R.id.show_viewpagerfragment)
    public Button mShowViewpagerfragment;

    @BindView(R.id.show_rxjava)
    public Button mShowRxjava;

    @BindView(R.id.show_rxjava2)
    public Button mShowRxjava2;

    @BindView(R.id.show_retrofit)
    public Button mShowRetrofit;

    @BindView(R.id.show_hellochart)
    public Button mShowHellochart;

    @BindView(R.id.show_event)
    public Button mShowEvent;

    @BindView(R.id.show_h5)
    public Button mShowH5;

    @BindView(R.id.show_tablayout)
    public Button mShowTablayout;

    @BindView(R.id.show_custom)
    public Button mShowCustom;

    @BindView(R.id.show_H5ToAndroid)
    public Button mShowH5ToAndroid;

    @BindView(R.id.show_property_animation)
    public Button mShowPropertyAnimation;

    @BindView(R.id.show_list)
    public Button mShowList;

    @BindView(R.id.show_list_2)
    public Button mShowList_2;

    @BindView(R.id.show_list_3)
    public Button mShowList3;

    @BindView(R.id.show_crash)
    public Button mShowCrash;

    @BindView(R.id.shareSdk)
    public Button mShowSDK;

    @BindView(R.id.shareSlideShow)
    public Button mShowSlideShow;

    @BindView(R.id.shareScrollView)
    public Button mShowScrollView;

    @BindView(R.id.showphoto)
    public Button showphoto;

    @BindView(R.id.showrecyclermore)
    public Button showrecyclermore;

    @BindView(R.id.showsecondActivity)
    public Button showsecondActivity;

    @BindView(R.id.showLongImage)
    public Button showLongImage;

    @BindView(R.id.showWebsocket)
    public Button showWebsocket;

    @BindView(R.id.okhttpActivity)
    public Button okhttpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void initPresenter() {

    }

    @OnClick(R.id.button1)
    public void goButton1() {
        startActivity(new Intent(MainActivity.this, buttonActivity.class));
    }

    @OnClick(R.id.button2)
    public void goButton2() {
        Intent intent = new Intent(MainActivity.this, popupwindowActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.request_movie)
    public void requestMovie() {

        String url = "http://10.24.1.214:8888/sy/rest/user/login";

//        ?username=admin&password=admin

        OkHttpUtils
                .post()
                .url(url)
                .addParams("username", "admin")
                .addParams("password", "admin")
                .build()

                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String ss = response.body().string();
                        return ss;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("onError", e.toString());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        Log.e("onResponse", response.toString());
                    }
                });

//        OkHttpUtils
//                .get()
//                .url("http://op.juhe.cn/onebox/movie/video")
//                .addParams("key", JuheDemo.APPKEY)
//                .addParams("dtype", "")
//                .addParams("q", "大话西游")
//                .build()
//                .execute(new StringCallback() {
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.d("response_log", response);
//                    }
//
//                });

//        OkHttpUtils
//                .get()
//                .url("http://sacasnap.neusoft.com/mailclient/mail/receiver/rest/getContent")
//                .addParams("mailId", "7663c3f5e87c4744bd87d8a7f9238219")
//                .build()
//                .execute(new StringCallback() {
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.d("response_log", response);
//                    }
//
//                });

    }

    @OnClick(R.id.show_and_hide)
    public void goShowAndHide() {
        Intent intent = new Intent(MainActivity.this, showandhideActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.call_phone)
    public void goCallPhone() {
        Intent intent = new Intent(MainActivity.this, callphoneActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.show_constraint)
    public void showConstraint() {
        Intent intent = new Intent(MainActivity.this, ConstraintActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.show_viewpager)
    public void showViewpager() {
        startActivity(new Intent(MainActivity.this, viewpagerActivity.class));
    }

    @OnClick(R.id.show_viewpagerfragment)
    public void showViewpagerfragment() {
        startActivity(new Intent(MainActivity.this, viewpagerfragmentActivity.class));
    }

    @OnClick(R.id.show_rxjava)
    public void showRxjava() {
        startActivity(new Intent(MainActivity.this, rxjava1Activity.class));
    }

    @OnClick(R.id.show_rxjava2)
    public void showRxjava2() {
        startActivity(new Intent(MainActivity.this, rxjava2Activity.class));
    }

    @OnClick(R.id.show_rxjava2_anti_shake)
    public void show_rxjava2_anti_shake() {
        startActivity(new Intent(MainActivity.this, rxjava2AntiShakeActivity.class));
    }


    @OnClick(R.id.show_retrofit)
    public void showRetrofit() {
        startActivity(new Intent(MainActivity.this, WelfareActivity.class));
    }

    @OnClick(R.id.show_hellochart)
    public void showHelloChart() {
        startActivity(new Intent(MainActivity.this, HelloChartsActivity.class));
    }

    @OnClick(R.id.show_event)
    public void showEvent() {
        startActivity(new Intent(MainActivity.this, EventTestActivity.class));
    }

    @OnClick(R.id.show_h5)
    public void showH5() {
        startActivity(new Intent(MainActivity.this, h5Activity.class));
    }

    @UIEventHandler(UIEventType.ClearChatLog)
    public void showEvent(UIEvent uiEvent) {
//        Log.d(MyApplication.EVENT_DEBUG, "showEvent");
//        Toast.makeText(this, this.getClass() + "UIEventHandler 被点击了！！！", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.show_tablayout)
    public void showTabLayout() {
        startActivity(new Intent(MainActivity.this, tabLayoutActivity.class));
    }

    @OnClick(R.id.show_custom)
    public void showCustom() {
        startActivity(new Intent(MainActivity.this, customActivity.class));
    }

    @OnClick(R.id.show_H5ToAndroid)
    public void ShowH5ToAndroid() {
        startActivity(new Intent(MainActivity.this, H5ToAndroidActivity.class));
    }

    @OnClick(R.id.show_property_animation)
    public void Showanimation() {
        startActivity(new Intent(MainActivity.this, AnimationActivity.class));
    }

    @OnClick(R.id.show_list)
    public void Showlist() {
        startActivity(new Intent(MainActivity.this, listviewActivity.class));
    }

    @OnClick(R.id.show_list_2)
    public void Showlist2() {
        startActivity(new Intent(MainActivity.this, listview2Activity.class));
    }

    @OnClick(R.id.show_list_3)
    public void Showlist3() {
        startActivity(new Intent(MainActivity.this, ScrollView1Activity.class));
    }

    @OnClick(R.id.show_crash)
    public void ShowCrash() {
        Intent intent = new Intent();
        startActivity(intent);
    }

    @OnClick(R.id.shareSlideShow)
    public void showSlideShow() {
        startActivity(new Intent(MainActivity.this, SlideShowActivity.class));
    }

    @OnClick(R.id.shareScrollView)
    public void showScrollView() {
        startActivity(new Intent(MainActivity.this, ScrollView2Activity.class));
    }

    @OnClick(R.id.showphoto)
    public void showPhoto() {
        startActivity(new Intent(MainActivity.this, photoActivity.class));
    }

    @OnClick(R.id.showrecyclermore)
    public void showrecyclermore() {
        startActivity(new Intent(MainActivity.this, RecyclerViewMoreActivity.class));
    }


    @OnClick(R.id.showsecondActivity)
    public void showsecondActivity() {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }

    @OnClick(R.id.showLongImage)
    public void showLongImage() {
        startActivity(new Intent(MainActivity.this, LongImageActivity.class));
    }

    @OnClick(R.id.showWebsocket)
    public void showWebsocket() {
        startActivity(new Intent(MainActivity.this, websocketActivity.class));
    }

    @OnClick(R.id.okhttpActivity)
    public void okhttpActivity() {
        startActivity(new Intent(MainActivity.this, okhttpActivity.class));
    }
}
