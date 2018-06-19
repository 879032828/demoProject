package com.example.administrator.view_test.ViewPager.viewpagerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Button;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class viewpagerfragmentActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    public ViewPager mViewPager;

    @BindView(R.id.button1)
    public Button button1;

    @BindView(R.id.button2)
    public Button button2;

    private FragmentManager mFragmentmanager;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private viewpagerfragmentAdapter mViewPagerFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mFragmentmanager = getSupportFragmentManager();
        initFragmetList();
        mViewPagerFragmentAdapter = new viewpagerfragmentAdapter(mFragmentmanager, mFragmentList);
        initViewPager();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpagerfragment;
    }

    @Override
    public void initPresenter() {

    }

    public void initFragmetList() {
        Fragment firstFragment = new firstFragment();
        Fragment secondFragment = new secondFragment();
        mFragmentList.add(firstFragment);
        mFragmentList.add(secondFragment);
    }

    public void initViewPager() {
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setCurrentItem(0);
    }

    @OnClick(R.id.button1)
    public void selectitem1(){
        mViewPager.setCurrentItem(0);
    }

    @OnClick(R.id.button2)
    public void selectitem2(){
        mViewPager.setCurrentItem(1);
    }
}
