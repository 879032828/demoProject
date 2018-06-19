package com.example.administrator.view_test.ViewPager.viewpagerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.view_test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class secondFragment extends Fragment {

    View mView;

    @BindView(R.id.fragment_text)
    public TextView mFragmentText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_layout, null);
        }
        ButterKnife.bind(this,mView);//绑定framgent

        mFragmentText.setText("呵呵");
        return mView;
    }
}
