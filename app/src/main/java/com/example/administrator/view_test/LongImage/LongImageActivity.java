package com.example.administrator.view_test.LongImage;

import android.os.Bundle;


import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import java.io.IOException;
import java.io.InputStream;

public class LongImageActivity extends BaseActivity {

    private BigView bigView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bigView = (BigView) findViewById(R.id.bigview);

        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("horizontal.jpg");
            bigView.setImage(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_longimage;
    }

    @Override
    public void initPresenter() {

    }
}
