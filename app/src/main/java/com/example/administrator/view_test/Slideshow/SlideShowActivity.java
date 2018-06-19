package com.example.administrator.view_test.Slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
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
import com.example.administrator.view_test.gank.activity.BigImageActivity;
import com.example.administrator.view_test.gank.activity.photoDataAdapter;
import com.example.administrator.view_test.gank.bean.PhotoGirl;
import com.example.administrator.view_test.gank.contract.WelfareContract;
import com.example.administrator.view_test.gank.presenter.PhotoDataPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SlideShowActivity extends BaseActivity {

    private List<Bean> beanList = new ArrayList<>();

    private List<Integer> images = Arrays.asList(R.drawable.ssdk_oks_classic_alipay, R.drawable.ssdk_oks_classic_wechat, R.drawable.ssdk_oks_classic_qq);

    private List<String> titles = Arrays.asList("新闻一", "新闻二", "新闻三");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        detailUse();

        initView();
    }

    private void easyUse() {
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader(this));
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void detailUse() {
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader(this));
        //设置图片集合
        banner.setImages(beanList);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_slideshow;
    }

    @Override
    public void initPresenter() {

    }

    public void initView() {

    }

    private void initData() {
        for (int i = 0; i < images.size(); i++) {
            Bean bean = new Bean();
            bean.setId(i);
            bean.setImageUrl(images.get(i));
            bean.setContentUrl(titles.get(i));
            beanList.add(bean);
        }
    }

    public class Bean {

        private int id;
        private int ImageUrl;
        private String contentUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(int imageUrl) {
            ImageUrl = imageUrl;
        }

        public String getContentUrl() {
            return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
            this.contentUrl = contentUrl;
        }

    }
}
