package com.example.administrator.view_test.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.view_test.R;

/**
 * Created by Administrator on 2017/3/5 0005.
 */

public class TopBar extends RelativeLayout{

    private String mTitle;
    private float mTitleTextSize;
    private int mTitleTextColor;

    private int mLeftTextColor;
    private Drawable mLeftBackGround;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackGround;
    private String mRightText;

    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    private LayoutParams mLeftParam;
    private LayoutParams mRightParam;
    private LayoutParams mTitleParam;

    public TopBar(Context context) {
        super(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initView(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mTitle = ta.getString(R.styleable.TopBar_title);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);

        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackGround = ta.getDrawable(R.styleable.TopBar_leftBackGround);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackGround = ta.getDrawable(R.styleable.TopBar_rightBackGround);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackGround);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackGround);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParam = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftButton, mLeftParam);

        mRightParam = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParam);

        mTitleParam = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTitleParam.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE);
        addView(mTitleView, mTitleParam);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopbarClickListener != null){
                    mTopbarClickListener.leftClick();
                }
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopbarClickListener != null){
                    mTopbarClickListener.rightClick();
                }
            }
        });
    }

    private topbarClickListener mTopbarClickListener;
    public interface topbarClickListener{
        void leftClick();
        void rightClick();
    }

    public void setOnTopbarClickListener(topbarClickListener topbarClickListener){
        this.mTopbarClickListener = topbarClickListener;
    }

}
