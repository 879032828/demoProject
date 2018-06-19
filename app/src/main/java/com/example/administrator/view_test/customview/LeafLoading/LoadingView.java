package com.example.administrator.view_test.customview.LeafLoading;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.view_test.R;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/5/5 0005.
 */

public class LoadingView extends RelativeLayout {

    private Context mContext;
    private ImageView mFanView;
    private LeafLoadingView mLeafLoadingView;
    private LayoutInflater mLayoutInflater;

    private float fanviewWidth;
    private float fanviewHeight;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.update_item, null);
        mFanView = (ImageView) view.findViewById(R.id.fanview);
        mLeafLoadingView = (LeafLoadingView) view.findViewById(R.id.leafLoadingView);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.loadingview);
        fanviewWidth = ta.getDimension(R.styleable.loadingview_fanviewWidth, 10);
        fanviewHeight = ta.getDimension(R.styleable.loadingview_fanviewHeight, 10);

        setProty();

        ta.recycle();
    }

    private void setProty(){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mFanView.getLayoutParams();
        params.width = dip2px(mContext, fanviewWidth);
        params.height = dip2px(mContext, fanviewHeight);
        // params.setMargins(dip2px(Main2Activity.this, 1), 0, 0, 0); // 可以实现设置位置信息，如居左距离，其它类推
        // params.leftMargin = dip2px(Main2Activity.this, 1);
        mFanView.setLayoutParams(params);
    }

    /**
     * dp转为px
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    private int dip2px(Context context,float dipValue)
    {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }
}
