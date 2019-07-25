package com.example.administrator.view_test.LenTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;

import com.example.administrator.view_test.R;


public class leanTextView extends AppCompatTextView {

    private String TAG = "leanTextView";

    private Context mContext;

    public leanTextView(Context context) {
        this(context, null);
    }

    public leanTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public leanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        this.setGravity(Gravity.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.color_5c63f4));
        paint.setAntiAlias(true);//消除锯齿
        paint.setStrokeWidth(5);
        paint.clearShadowLayer();
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(getWidth() / 2, 0);
        path.lineTo(getWidth(), 0);
        path.lineTo(0, getHeight());
        path.lineTo(0, getWidth() / 2);
        path.close();
        canvas.drawPath(path, paint);


        double third = Math.sqrt(getWidth() / 2 * getWidth() / 2 + getHeight() / 2 * getHeight() / 2);
        double target = Math.cos(Math.toRadians(45)) * third * 3 / 4;
        float offset = (float) (getWidth() / 2 - target);
        canvas.save();
        canvas.translate(getCompoundPaddingLeft() - offset, getExtendedPaddingTop() - offset);

        canvas.rotate(-45, this.getWidth() / 2f, this.getHeight() / 2f);

        float width = this.getPaint().measureText(getText().toString());
        Log.d(TAG, "text width" + width);

        super.onDraw(canvas);
        canvas.restore();
    }

}
