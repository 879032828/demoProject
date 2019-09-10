package com.example.administrator.view_test.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.view_test.R;

/**
 * Created by keer on 2016/12/8.
 */

public class BaseDialog extends Dialog {
    protected String TAG;
    protected TextView tvTitle;
    protected TextView tvConfirm;
    protected TextView tvCancel;
    protected View contentLayout;

    /**
     * 默认dialog占屏幕宽度的比例
     */
    protected static final float WINDOW_WIDTH_RATIO = 0.7f;
    protected Context mContext;

    public BaseDialog(Context context) {
        this(context, R.style.dialog_standard);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        TAG = getClass().getSimpleName();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
    }

    protected void setDialogSize(int width, int height) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.height = height;//宽高可设置具体大小
        lp.width = width;
        window.setAttributes(lp);
    }

    protected void setDefaultWidth() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (getScreenWidth() * WINDOW_WIDTH_RATIO);
        window.setAttributes(lp);
    }

    protected int getScreenWidth() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }

    protected int dp2px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics()) + 0.5f);
    }

    public static abstract class BaseBuilder {
        protected Context context;
        /**
         * 默认0，采用系统默认样式
         */
        protected int style = 0;

        public BaseBuilder(Context context) {
            this.context = context;
        }
    }
}
