package com.example.administrator.view_test.customview.LeafLoading;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class LeafLoadingView extends View {

    private static final String LEAFLOADING_TAG = "LeafLoadingView";

    private Context mContext;

    private Resources mResources;
    private Bitmap mLeafBitmap;
    private Bitmap mOuterBitmap;
    private Bitmap mFanBitmap;
    private Bitmap scaledBitmap;
    private int mLeafWidth, mLeafHeight;
    private int mOuterWidth, mOuterHeight;
    private int mFanWidth, mFanHeight;

    private Paint mBitmapPaint;
    private Paint mOuterPaint;
    private Paint mWhitePaint;
    private Paint mOrangePaint;
    private Paint mCircleWhitePaint;

    // 用于控制绘制的进度条距离左／上／下的距离
    private int LEFT_MARGIN = 9;
    // 用于控制绘制的进度条距离左／上／下的距离的倍数
    private int LEFT_MARGIN_MULTIPLE = 18;
    // 用于控制绘制的进度条距离右的距离
    private int RIGHT_MARGIN = 25;
    // 用于控制绘制的进度条距离右的距离的倍数
    private int RIGHT_MARGIN_MULTIPLE = 6;
    //风扇缩放尺寸
    private int SCALE_SIZE;
    //风扇缩放倍数
    private int SCALE_TIME = 2;
    private int mLeftMargin, mRightMargin;
    private int mTotalWidth, mTotalHeight;
    private Rect mFanSrcRect, mFanDestRect;
    private Rect mOuterSrcRect, mOuterDestRect;
    private RectF mProcessWhiteRectF, mProcessOrangeRectF, mProcessArcRectF, mCircleRectF;
    private RectF mOuterArcRectF, mOuterRectF;
    // 当前所在的绘制的进度条的位置
    private int mCurrentProgressPosition;
    // 所绘制的进度条部分的宽度
    private int mProgressWidth;
    // 弧形的半径
    private int mArcRadius;
    // arc的右上角的x坐标，也是矩形x坐标的起始点
    private int mArcRightLocation;
    // 总进度
    private static final int TOTAL_PROGRESS = 100;
    //当前进度
    private int mProgress;
    // 淡白色
    private static final int LIGHT_WHITE_COLOR = 0xfffde399;
    // 橙色
    private static final int ORANGE_COLOR = 0xffffa800;
    //白色
    private static final int WHITE_COLOR = 0xffffffff;

    private Canvas mCanvas;
    private Matrix mMatrix;
    private Bitmap bitmap;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private int rotate = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            postInvalidate();
        }
    };

    public LeafLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mResources = getResources();
        mContext = context;
        initBitmap();
        initPaint();
        mTimer = new Timer();

        mMatrix = new Matrix();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fengshan, options);

        initAnimator();
        recycleTurn();//启动旋转
    }

    private void initAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMatrix, "lc", 0, 360);
        animator.setDuration(1500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                Log.d(LEAFLOADING_TAG, "getAnimatedValue" + f);
                mMatrix.setRotate(f);
                invalidate();
            }
        });

    }

    public LeafLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initBitmap() {
        mLeafBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.leaf)).getBitmap();
        mLeafWidth = mLeafBitmap.getWidth();
        mLeafHeight = mLeafBitmap.getHeight();

        mOuterBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.leaf_kuang)).getBitmap();
        mOuterWidth = mOuterBitmap.getWidth();
        mOuterHeight = mOuterBitmap.getHeight();

        mFanBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.fengshan)).getBitmap();
        mFanWidth = mFanBitmap.getWidth();
        mFanHeight = mFanBitmap.getHeight();
    }

    private void initPaint() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);//抗锯齿
        mBitmapPaint.setDither(true);//设置防抖动
        mBitmapPaint.setFilterBitmap(true);//也是抗锯齿的一个方法

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setColor(LIGHT_WHITE_COLOR);

        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setColor(LIGHT_WHITE_COLOR);

        mOrangePaint = new Paint();
        mOrangePaint.setAntiAlias(true);
        mOrangePaint.setColor(ORANGE_COLOR);

        mCircleWhitePaint = new Paint();
        mCircleWhitePaint.setAntiAlias(true);
        mCircleWhitePaint.setColor(WHITE_COLOR);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(LEAFLOADING_TAG, "onSizeChanged");
        Log.d(LEAFLOADING_TAG, "mTotalWidth : " + w);
        Log.d(LEAFLOADING_TAG, "mTotalHeight : " + h);
        mTotalWidth = w;
        mTotalHeight = h;

        SCALE_SIZE = h / SCALE_TIME;
        Log.d(LEAFLOADING_TAG, "SCALE_SIZE : " + SCALE_SIZE);

        LEFT_MARGIN = mTotalHeight / LEFT_MARGIN_MULTIPLE;
        Log.d(LEAFLOADING_TAG, "LEFT_MARGIN : " + LEFT_MARGIN);

        RIGHT_MARGIN = mTotalHeight / RIGHT_MARGIN_MULTIPLE;
        Log.d(LEAFLOADING_TAG, "RIGHT_MARGIN : " + RIGHT_MARGIN);

        mLeftMargin = UiUtils.dipToPx(mContext, LEFT_MARGIN);
        mRightMargin = UiUtils.dipToPx(mContext, RIGHT_MARGIN);

        mProgressWidth = mTotalWidth - mLeftMargin - mRightMargin;
        mArcRadius = (mTotalHeight - 2 * mLeftMargin) / 2;

        //第一个Rect 代表要绘制的bitmap 区域，第二个 Rect 代表的是要将bitmap 绘制在屏幕的什么地方
        Log.d(LEAFLOADING_TAG, "mFanWidth : " + mFanWidth);
        Log.d(LEAFLOADING_TAG, "mFanHeight : " + mFanHeight);
        //bmp.getWidth(), bmp.getHeight()分别表示缩放后的位图宽高
        scaledBitmap = Bitmap.createScaledBitmap(mFanBitmap, SCALE_SIZE, SCALE_SIZE, false);
        Log.d(LEAFLOADING_TAG, "dstbmp.getWidth() :" + scaledBitmap.getWidth());
        Log.d(LEAFLOADING_TAG, "dstbmp.getHeight() :" + scaledBitmap.getHeight());
        mFanSrcRect = new Rect(0, 0, mTotalWidth, mTotalHeight);
        mFanDestRect = new Rect(
                mTotalWidth - mTotalHeight / 2 - scaledBitmap.getWidth() / 2,
                mTotalHeight / 2 - scaledBitmap.getHeight() / 2,
                mTotalWidth - mTotalHeight / 2 + scaledBitmap.getWidth() / 2,
                mTotalHeight / 2 + scaledBitmap.getHeight() / 2);

        //外框圆弧
        mOuterArcRectF = new RectF(0, 0, mTotalHeight, mTotalHeight);
        //外框矩形
        mOuterRectF = new RectF(mTotalHeight / 2, 0, mTotalWidth - mTotalHeight / 2, mTotalHeight);

        //进度条白色矩形
        mProcessWhiteRectF = new RectF(mLeftMargin + mCurrentProgressPosition, mLeftMargin, mTotalWidth
                - mRightMargin,
                mTotalHeight - mLeftMargin);
        //进度条橙色矩形
        mProcessOrangeRectF = new RectF(mLeftMargin + mArcRadius, mLeftMargin,
                mCurrentProgressPosition
                , mTotalHeight - mLeftMargin);
        //进度条圆弧
        mProcessArcRectF = new RectF(mLeftMargin, mLeftMargin, mLeftMargin + 2 * mArcRadius,
                mTotalHeight - mLeftMargin);
        mArcRightLocation = mLeftMargin + mArcRadius;

        mCircleRectF = new RectF(mTotalWidth - RIGHT_MARGIN, 0, mTotalWidth, mTotalHeight);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(LEAFLOADING_TAG, "onDraw");
        mCanvas = canvas;
        drawProgressAndLeafs(canvas);
//        FanAnimation(canvas);
    }

    private void drawProgressAndLeafs(Canvas canvas) {

        Log.d(LEAFLOADING_TAG, "mProgress" + mProgress);
        if (mProgress >= TOTAL_PROGRESS) {
            mProgress = 0;
            mCurrentProgressPosition = 0;
        }
        // mProgressWidth为进度条的宽度，根据当前进度算出进度条的位置
        mCurrentProgressPosition = mProgressWidth * mProgress / TOTAL_PROGRESS;

        if (mCurrentProgressPosition < mArcRadius) {
            // 1.绘制外框ARC和白色ARC
            canvas.drawArc(mOuterArcRectF, 90, 180, false, mOuterPaint);
            canvas.drawArc(mProcessArcRectF, 90, 180, false, mWhitePaint);


            // 2.绘制外框矩形和白色矩形
            mProcessWhiteRectF.left = mArcRightLocation;
            canvas.drawRect(mOuterRectF, mOuterPaint);
            canvas.drawRect(mProcessWhiteRectF, mWhitePaint);

            //绘制橙色圆弧
            //单边角度
            int angle = (int) Math.toDegrees(Math.acos((mArcRadius - mCurrentProgressPosition)
                    / (float) mArcRadius));
            // 起始的位置
            int startAngle = 180 - angle;
            // 扫过的角度
            int sweepAngle = 2 * angle;

            canvas.drawArc(mProcessArcRectF, startAngle, sweepAngle, false, mOrangePaint);
        } else {
            // 1.绘制white RECT
            // 2.绘制Orange ARC
            // 3.绘制orange RECT
            // 这个层级进行绘制能让叶子感觉是融入棕色进度条中

            Log.d(LEAFLOADING_TAG, "mCurrentProgressPosition >= mArcRadius");
            Log.d(LEAFLOADING_TAG, "mCurrentProgressPosition : " + mCurrentProgressPosition);
            // 1.绘制外框矩形和white RECT
            mProcessWhiteRectF.left = mArcRightLocation;
            canvas.drawRect(mOuterRectF, mOuterPaint);
            canvas.drawRect(mProcessWhiteRectF, mWhitePaint);

            //2、绘制外框ARC和Orange ARC
            canvas.drawArc(mOuterArcRectF, 90, 180, false, mOuterPaint);
            canvas.drawArc(mProcessArcRectF, 90, 180, false, mOrangePaint);


            //3、绘制orange RECT
            mProcessOrangeRectF.left = mArcRightLocation;
            mProcessOrangeRectF.right = mCurrentProgressPosition;
            canvas.drawRect(mProcessOrangeRectF, mOrangePaint);
        }

        //绘制两个同心圆
        canvas.drawCircle(mTotalWidth - mTotalHeight / 2, mTotalHeight / 2, mTotalHeight / 2, mCircleWhitePaint);
        canvas.drawCircle(mTotalWidth - mTotalHeight / 2, mTotalHeight / 2, mTotalHeight / 2 - mTotalHeight / 12, mOrangePaint);
        Log.d(LEAFLOADING_TAG, "drawCircle x :" + (mTotalWidth - mTotalHeight / 2) + "y :" + mTotalHeight / 2);

//        Matrix matrix=new Matrix();
//        int width=scaledBitmap.getWidth();
//        int height=scaledBitmap.getHeight();
//        matrix.postRotate(45f, width/2, height/2);
//        scaledBitmap = Bitmap.createBitmap(scaledBitmap, width/2, height/2, width, height, matrix, true);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        this.mProgress = progress;
        postInvalidate();//执行该函数时，会重新调用onDraw方法
    }

    private void FanAnimation(Canvas canvas) {
        Log.d(LEAFLOADING_TAG, "handleMessage");
        scaledBitmap = Bitmap.createScaledBitmap(mFanBitmap, SCALE_SIZE, SCALE_SIZE, false);
        int width = scaledBitmap.getWidth();
        int height = scaledBitmap.getHeight();
        Log.d(LEAFLOADING_TAG, "新建scaledBitmap后的SCALE_SIZE" + SCALE_SIZE);
        Log.d(LEAFLOADING_TAG, "新建scaledBitmap后的宽度" + width);
        Log.d(LEAFLOADING_TAG, "新建scaledBitmap后的高度" + height);

        //加载风扇图
//        canvas.translate(mTotalHeight / 2, mTotalHeight / 2);
//        canvas.drawBitmap(scaledBitmap, mFanSrcRect, mFanDestRect, mBitmapPaint);


        canvas.translate(mTotalWidth / 2, mTotalHeight / 2);

        mMatrix.preTranslate(-scaledBitmap.getWidth() / 2, -scaledBitmap.getHeight() / 2);
        Log.d(MyApplication.CUSTOM_DEBUG, "mMatrix rotate :" + rotate);
        canvas.drawBitmap(scaledBitmap, mMatrix, null);
    }

    boolean isFinish = false;

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    private void recycleTurn() {
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(MyApplication.CUSTOM_DEBUG, "getAnimatedValue rotate :" + rotate);
//                mMatrix.setRotate(rotate);
//                rotate += 3;
//                postInvalidate();
//                if (!isFinish) {
//                    recycleTurn();
//                }
//            }
//        }, 100);
    }
}
