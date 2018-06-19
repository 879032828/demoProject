package com.example.administrator.view_test.customview.LeafLoading;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.view_test.R;

/**
 * Created by Administrator on 2017/5/5 0005.
 */

public class fanView extends View {

    private Bitmap mFanBitmap;
    private Resources mResources;
    private Paint mBitmapPaint;

    public fanView(Context context) {
        this(context, null);
    }

    public fanView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mResources = getResources();

        initBitmap();
        initPaint();
    }

    private void initBitmap() {
        mFanBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.fengshan)).getBitmap();
    }

    private void initPaint() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);//抗锯齿
        mBitmapPaint.setDither(true);//设置防抖动
        mBitmapPaint.setFilterBitmap(true);//也是抗锯齿的一个方法
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mFanBitmap, 0, 0, mBitmapPaint);
    }
}
