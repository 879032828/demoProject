package com.example.administrator.view_test.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.administrator.view_test.MyApplication;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Canvascustomview extends View {

    private Paint mPaint = new Paint();
    private int screenHight;
    private int screenWidth;

    //1、构造函数 ---- 初始化(初始化画笔Paint)
    public Canvascustomview(Context context) {
        this(context, null);
    }

    public Canvascustomview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Canvascustomview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(MyApplication.CUSTOM_DEBUG, "1、Canvascustomview");
    }

    //2、onMeasure ---- 测量View的大小(暂时不用关心)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(MyApplication.CUSTOM_DEBUG, "2、onMeasure");
    }

    //3、onSizeChanged ---- 确定View大小(记录当前View的宽高)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(MyApplication.CUSTOM_DEBUG, "3、onSizeChanged");
    }

    //4、onLayout ---- 确定子View布局(无子View，不关心)
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(MyApplication.CUSTOM_DEBUG, "4、onLayout");
    }

    //5、onDraw ---- 实际绘制内容(绘制饼状图)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        initParams(canvas);
        //Canvas之绘制图形
//        drawPoint(canvas);
//        drawLine(canvas);
//        drawRect(canvas);
//        drawRoundRect(canvas);
//        drawOval(canvas);
//        drawCircle(canvas);
//        drawArc(canvas);
//        PaintStyle(canvas);

        //Canvas之画布操作
//        translate(canvas);
//        scale(canvas);
//        rotate(canvas);

        drawLoading(canvas);

        Log.d(MyApplication.CUSTOM_DEBUG, "5、onDraw");
    }

    //6、提供接口(提供设置数据的接口)

    private void initParams(Canvas canvas) {

        screenWidth = canvas.getWidth();
        screenHight = canvas.getHeight();
        Log.d(MyApplication.CUSTOM_DEBUG, "屏幕高度" + screenHight);
        Log.d(MyApplication.CUSTOM_DEBUG, "屏幕宽度" + screenWidth);
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
    }

    private void drawPoint(Canvas canvas) {
        canvas.drawPoint(200, 200, mPaint);     //在坐标(200,200)位置绘制一个点
        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
                500, 500,
                500, 600,
                500, 700
        }, mPaint);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(300, 300, 400, 400, mPaint);     // 在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(new float[]{          // 绘制一组线 每四数字(两个点的坐标)确定一条线
                500, 500, 600, 600,
                700, 700, 800, 800
        }, mPaint);
    }

    private void drawRect(Canvas canvas) {  //绘制矩形
        // 第一种
//        canvas.drawRect(100,100,800,400,mPaint);

        // 第二种
//        Rect rect = new Rect(100,100,800,400);
//        canvas.drawRect(rect, mPaint);

        // 第三种
        RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawRect(rectF, mPaint);
    }

    private void drawRoundRect(Canvas canvas) {  //绘制圆角矩形
        // 第一种
        RectF rectF = new RectF(500, 500, 800, 800);
        canvas.drawRoundRect(rectF, 30, 30, mPaint);

        // 第二种
        //但鉴于第二种方法在API21的时候才添加上，所以我们一般使用的都是第一种
//        canvas.drawRoundRect(100,100,800,400,30,30,mPaint);
    }

    private void drawOval(Canvas canvas) {  //绘制椭圆
        // 第一种
        RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawOval(rectF, mPaint);

        // 第二种
//        canvas.drawOval(100,100,800,400,mPaint);
    }

    private void drawCircle(Canvas canvas) { //绘制圆
        canvas.drawCircle(500, 500, 400, mPaint);// 绘制一个圆心坐标在(500,500)，半径为400 的圆。
    }

    private void drawArc(Canvas canvas) {   //绘制圆弧

        RectF rectF1 = new RectF(100, 500, 200, 600);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF1, mPaint);

        //startAngle  // 开始角度           0
        //sweepAngle  // 扫过角度           90
        //useCenter   // 是否使用中心         true
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF1, 0, 30, true, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF1, 30, 90, true, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF1, 90, 270, true, mPaint);

    }

    private void PaintStyle(Canvas canvas) {
        //绘制的基本形状由Canvas确定，但绘制出来的颜色,具体效果则由Paint确定
        //  STROKE                //描边
        //  FILL                  //填充
        //  FILL_AND_STROKE       //描边加填充
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(40);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300, 300, 200, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(300, 800, 200, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(300, 1300, 200, paint);
    }

    //平移
    private void translate(Canvas canvas) {

        //translate是坐标系的移动，可以为图形绘制选择一个合适的坐标系。
        // 请注意，位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动
        //第二次移动时，起始位置坐标是200，200，最后的坐标是400，400
        mPaint.setColor(Color.BLACK);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
    }

    //缩放
    private void scale(Canvas canvas) {
        //如果在缩放时稍微注意一下就会发现缩放的中心默认为坐标原点,而缩放中心轴就是坐标轴

//        //// 将坐标系原点移动到画布正中心
//        canvas.translate(screenWidth / 2, screenHight / 2);
//
//        RectF rect = new RectF(0, -400, 400, 0);   // 矩形区域
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rect, mPaint);
//
////        canvas.scale(-1f, -1f);//根据缩放中心轴进行翻转
//        canvas.scale(0.5f, 0.5f);//根据缩放中心缩小到n
//
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rect, mPaint);


        canvas.translate(screenWidth / 2, screenHight / 2);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rect = new RectF(-400, -400, 400, 400);   // 矩形区域

        for (int i = 0; i <= 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rect, mPaint);
        }
    }

    //旋转
    private void rotate(Canvas canvas) {
        // 将坐标系原点移动到画布正中心
        canvas.translate(screenWidth / 2, screenHight / 2);

        RectF rect = new RectF(0, -400, 400, 0);   // 矩形区域
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint);

        canvas.rotate(100);                     // 旋转180度 <-- 默认旋转中心为原点

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint);
    }

    private void drawLoading(Canvas canvas){

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);

        RectF arcRectf = new RectF(100, 100, 300, 300);

        canvas.translate(0, 200);
        canvas.drawArc(arcRectf, 90, 180, true, paint);

        RectF rectF = new RectF(200, 100, 1000, 300);
        canvas.drawRect(rectF, paint);
    }
}
