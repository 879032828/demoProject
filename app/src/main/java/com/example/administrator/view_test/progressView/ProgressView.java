package com.example.administrator.view_test.progressView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.view_test.R;


/**
 * Created by shu.xinghu on 2016/3/29.
 */
public class ProgressView extends View {

    private static final String TAG = "ProgressView";

    private Context mContext;

    private int width = 20;
    private int height = 20;
    private int defaultTotalProgress = 10;
    /**
     * 默认高度
     */
    private int defaultHeight = 200;
    /**
     * 默认宽度
     */
    private int defaultWidth = 200;
    /**
     * 进度
     */
    private int progress = 0;
    /**
     * 进度总数
     */
    private int totalProgress = defaultTotalProgress;
    /**
     * 背景画笔
     */
    private Paint bgPaint = new Paint();
    /**
     * 进度条画笔
     */
    private Paint progressPaint = new Paint();
    /**
     * 文字画笔
     */
    private Paint textPaint = new Paint();
    /**
     * 白色画笔
     */
    private Paint mWhitePaint = new Paint();
    /**
     * 进度条颜色
     */
    private int progressColor;
    /**
     * 进度文本大小
     */
    private float progressTextSize;
    /**
     * 进度文本颜色
     */
    private int progressTextColor;

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.progressView, defStyleAttr, defStyleRes);
        progressColor = a.getColor(R.styleable.progressView_progressViewColor, getResources().getColor(R.color.white));
        progressTextSize = a.getDimension(R.styleable.progressView_progressTextSize, 36);
        progressTextColor = a.getColor(R.styleable.progressView_progressTextColor, getResources().getColor(R.color.white));
        totalProgress = a.getInt(R.styleable.progressView_ratio, defaultTotalProgress);

        initPaint();

        a.recycle();
    }

    private void initPaint() {
        bgPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        bgPaint.setAntiAlias(true);//消除锯齿
        bgPaint.setStrokeWidth(0);
        bgPaint.clearShadowLayer();
        bgPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        progressPaint.setColor(getResources().getColor(R.color.color_fffd51));
        progressPaint.setAntiAlias(true);//消除锯齿
        progressPaint.setStrokeWidth(0);
        progressPaint.clearShadowLayer();
        progressPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint.setColor(progressTextColor);
        textPaint.setAntiAlias(true);//消除锯齿
        textPaint.setStrokeWidth(1);
        textPaint.clearShadowLayer();
        textPaint.setTextSize(progressTextSize);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        mWhitePaint.setColor(getResources().getColor(R.color.colorPrimary));
        mWhitePaint.setAntiAlias(true);//消除锯齿
        mWhitePaint.setStrokeWidth(1);
        mWhitePaint.clearShadowLayer();
        mWhitePaint.setStyle(Paint.Style.FILL_AND_STROKE);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = measureWidth(widthMeasureSpec);
        height = measureHeight(heightMeasureSpec);
        Log.d(TAG, "修改后的高的尺寸:" + height);
        Log.d(TAG, "修改后的宽的尺寸:" + width);
        //保存测量宽度和测量高度
        setMeasuredDimension(width, height);
    }

    /**
     * 计算宽度
     *
     * @param measureSpec
     * @return
     */
    private int measureWidth(int measureSpec) {
        int result = defaultWidth;
        int specMode = MeasureSpec.getMode(measureSpec);//获取宽的模式
        int specSize = MeasureSpec.getSize(measureSpec);//获取宽的尺寸
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST://如果是wrap_content，我们要得到控件需要多大的尺寸
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY://如果match_parent或者具体的值，直接赋值
                result = specSize;
                break;
        }
        return result;
    }

    /**
     * 计算高度
     *
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int result = defaultHeight;
        int specMode = MeasureSpec.getMode(measureSpec);//获取高的模式
        int specSize = MeasureSpec.getSize(measureSpec);//获取高的尺寸
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST://如果是wrap_content，我们要得到控件需要多大的尺寸
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY://如果match_parent或者具体的值，直接赋值
                result = specSize;
                break;
        }
        return result;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        if (progress > totalProgress) {
            return;
        }
        invalidate();
    }

    public void setTotalProgress(int totalProgress) {
        this.totalProgress = totalProgress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float bgWidth = width - getPaddingRight() - height;
        float ratio = bgWidth / totalProgress;
        float progressWidth = ratio * progress;
        Log.d(TAG, "bgWidth = " + bgWidth);
        Log.d(TAG, "ratio = " + ratio);

        drawBackground(canvas);

        if (progress <= totalProgress && progressWidth > 0) {
            drawProgress(canvas, progressWidth);
            drawProgressText(canvas, ratio);
        }
//        if (progress < totalProgress && progressWidth > 0) {
//            initRecf(progressWidth);
//            drawProgress(canvas, progressWidth, progressWidth);
//            drawProgressText(canvas, ratio);
//        }
    }

    /**
     * 绘制进度条背景
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        RectF mArcRectF = new RectF(0, 0, height, height);
        canvas.drawArc(mArcRectF, 90, 180, false, bgPaint);
        RectF mWhiteRectF = new RectF(height >> 1, 0, width - (height >> 1), height);
        canvas.drawRect(mWhiteRectF, bgPaint);
        mArcRectF = new RectF(width - height, 0, width, height);
        canvas.drawArc(mArcRectF, 270, 180, false, bgPaint);
    }

    float mCurrentProgressPosition;
    float mArcRadius;
    RectF mArcRectF;
    RectF mOrangeRectF;


//    private void drawProgress(Canvas canvas, float progressWidth) {
//
//        mArcRectF = new RectF(0, 0, progressWidth, height);
//
//        mArcRadius = height / 2;
//
//        // mProgressWidth为进度条的宽度，根据当前进度算出进度条的位置
//        mCurrentProgressPosition = this.width * progress / totalProgress;
//        if (mCurrentProgressPosition < mArcRadius) {
//            Log.i(TAG, "mProgress = " + progress + "---mCurrentProgressPosition = "
//                    + mCurrentProgressPosition
//                    + "--mArcProgressWidth" + mArcRadius);
//
//            // 单边角度
//            int angle = (int) Math.toDegrees(Math.acos((mArcRadius - mCurrentProgressPosition)
//                    / (float) mArcRadius));
//            // 起始的位置
//            int startAngle = 180 - angle;
//            // 扫过的角度
//            int sweepAngle = 2 * angle;
//            Log.i(TAG, "startAngle = " + startAngle);
//            canvas.drawArc(mArcRectF, startAngle, sweepAngle, false, progressPaint);
//        } else {
//            mArcRectF = new RectF(0, 0, height, height);
//            canvas.drawArc(mArcRectF, 90, 180, false, progressPaint);
//            mOrangeRectF = new RectF(0, 0, 0, height);
//            mOrangeRectF.left = height / 2;
//            mOrangeRectF.right = mCurrentProgressPosition;
////            if (mCurrentProgressPosition - height / 2 < mArcRadius) {
////                mOrangeRectF.right = mCurrentProgressPosition;
////            } else {
////                mOrangeRectF.right = mCurrentProgressPosition - height / 2;
////            }
//            canvas.drawRect(mOrangeRectF, progressPaint);
////            mArcRectF = new RectF(mCurrentProgressPosition, 0, mCurrentProgressPosition, height);
////            canvas.drawArc(mArcRectF, 270, 180, false, progressPaint);
//
//        }
//    }

    private void drawProgress(Canvas canvas, float progressWidth) {

        mArcRectF = new RectF(0, 0, progressWidth, height);

        mArcRadius = height >> 1;

        // mProgressWidth为进度条的宽度，根据当前进度算出进度条的位置
        mCurrentProgressPosition = (width - getPaddingRight() - height/2) * progress / totalProgress;

        if (mCurrentProgressPosition <= mArcRadius) {
            mArcRectF = new RectF(0, 0, height, height);
            canvas.drawArc(mArcRectF, 90, 360, false, progressPaint);
        }
        if (mArcRadius < mCurrentProgressPosition) {
            mArcRectF = new RectF(0, 0, height, height);
            canvas.drawArc(mArcRectF, 90, 180, false, progressPaint);
            mOrangeRectF = new RectF(0, 0, 0, height);
            mOrangeRectF.left = height >> 1;
            mOrangeRectF.right = mCurrentProgressPosition;
            canvas.drawRect(mOrangeRectF, progressPaint);
            mArcRectF = new RectF(mCurrentProgressPosition - (height >> 1), 0, mCurrentProgressPosition + (height >> 1), height);
            canvas.drawArc(mArcRectF, 270, 360, false, progressPaint);
        }
    }

    /**
     * 绘制进度
     *
     * @param canvas
     * @param ratio
     */
    private void drawProgressText(Canvas canvas, float ratio) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        Log.d(TAG, " fontMetrics.top = " + fontMetrics.top);
        Log.d(TAG, " fontMetrics.ascent = " + fontMetrics.ascent);
        Log.d(TAG, " fontMetrics.descent = " + fontMetrics.descent);
        Log.d(TAG, " fontMetrics.bottom = " + fontMetrics.bottom);
        Log.d(TAG, " fontHeight = " + (textPaint.ascent() + textPaint.descent()));

        float fontHeight = textPaint.descent() - textPaint.ascent();//字体的高度
        float baseline = fontHeight / 2 - fontMetrics.bottom;//字体中心点到基准线的距离
        float y = height / 2 + baseline;//基准线的Y轴位置，使字体居中
        canvas.drawText(this.progress + "/" + totalProgress, (float) (width * 0.8), y, textPaint);
    }
}
