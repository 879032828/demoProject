package com.example.administrator.view_test.TabLayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.Base.BaseFragmentAdapter;
import com.example.administrator.view_test.R;
import com.example.administrator.view_test.TabLayout.RYRY.SecondFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class tabLayoutActivity extends BaseActivity {

    @BindView(R.id.tabs)
    public TabLayout tabs;

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    private FragmentManager mFragmentManager;
    private BaseFragmentAdapter fragmentAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mChannelNames = new ArrayList<>();

    private String first_tag = "";
    private String second_tag = "";
    private String three_tag = "";
    private String four_tag = "";

    private HashMap<String, String> TagList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();
        initTablayout();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    public void initPresenter() {

    }

    private void initTablayout() {

//        Fragment firstfragment = new FirstFragment();
//        Fragment secondfragment = new SecondFragment();
//        Fragment threefragment = new ThreeFragment();
//        Fragment fourfragment = new FourFragment();

        Fragment firstfragment = new SecondFragment();
        Fragment secondfragment = new FirstFragment();
        Fragment threefragment = new FirstFragment();
        Fragment fourfragment = new FirstFragment();

        mFragmentList.add(firstfragment);
        mFragmentList.add(secondfragment);
        mFragmentList.add(threefragment);
        mFragmentList.add(fourfragment);

        mChannelNames.add("全部");
        mChannelNames.add("进行中");
        mChannelNames.add("待接收");
        mChannelNames.add("审批中");

        TagList.put("全部", "0");
        TagList.put("进行中", "1");
        TagList.put("待接收", "2");
        TagList.put("审批中", "3");

        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(mFragmentManager, mFragmentList, mChannelNames);
        } else {
            //刷新fragment
            fragmentAdapter.setFragments(mFragmentManager, mFragmentList, mChannelNames);
        }

        viewPager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        tabs.setTabMode(TabLayout.MODE_FIXED);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("选中的tab", tab.getText().toString());
                BaseFragment fragment = (BaseFragment) mFragmentManager.findFragmentByTag(fragmentAdapter.getTagList().get(tab.getText().toString()));
                if (fragment != null) {
                    Log.e("选中的fragment", tab.getText().toString());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("未选中的tab", tab.getText().toString());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("重复选中的tab", tab.getText().toString());
            }
        });
    }

}
