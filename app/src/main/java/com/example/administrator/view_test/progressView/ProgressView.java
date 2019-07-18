package com.example.administrator.view_test.progressView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
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
        bgPaint.setColor(getResources().getColor(R.color.color_e9e9e9));
        bgPaint.setAntiAlias(true);//消除锯齿
        bgPaint.setStrokeWidth(0);
        bgPaint.clearShadowLayer();
        bgPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        progressPaint.setColor(progressColor);
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
        invalidate();
    }

    public void setTotalProgress(int totalProgress) {
        this.totalProgress = totalProgress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float bgWidth = width - getPaddingRight();
        float ratio = bgWidth / totalProgress;
        float progressWidth = ratio * progress;
        Log.d(TAG, "bgWidth = " + bgWidth);
        Log.d(TAG, "ratio = " + ratio);

        //绘制背景条
        canvas.drawRoundRect(0 + getPaddingLeft(), 0 + getPaddingTop(), bgWidth, height - getPaddingBottom(), height / 2, height / 2, bgPaint);

        if (progress < totalProgress) {
            drawProgress(canvas, progressWidth);
            drawProgressText(canvas, ratio);
        }
    }

    /**
     * 根据进度绘制进度条
     *
     * @param canvas
     * @param progressWidth
     */
    private void drawProgress(Canvas canvas, float progressWidth) {
        if (progressWidth != 0) {
            if (progressWidth < height) {
                canvas.drawRoundRect(0 + getPaddingLeft(), 0 + getPaddingTop(), progressWidth, height - getPaddingBottom(), progressWidth / 2, height / 2, progressPaint);
            } else {
                canvas.drawRoundRect(0 + getPaddingLeft(), 0 + getPaddingTop(), progressWidth, height - getPaddingBottom(), height / 2, height / 2, progressPaint);
            }
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
