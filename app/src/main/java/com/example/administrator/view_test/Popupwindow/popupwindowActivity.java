package com.example.administrator.view_test.Popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class popupwindowActivity extends BaseActivity {

    @BindView(R.id.show_popupwindow)
    public Button mShowPopupwindow;

    @BindView(R.id.show_anim)
    public Button mShowAnim;

    @BindView(R.id.view_touming)
    public View view_touming;

    @BindView(R.id.client_phone_pupopwindow)
    public RelativeLayout mClientPhonePupopwindow;

    Animation scaleAnimation;
    PopupWindow popupWindow;

    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_popupwindow);

        datas.add("1");
        datas.add("2");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_popupwindow;
    }

    @Override
    public void initPresenter() {

    }

    public void showPopFormBottom() {
        MyPopupWindow takePhotoPopWin = new MyPopupWindow(this, null);
//        设置Popupwindow显示位置（从底部弹出）
        takePhotoPopWin.showAtLocation(findViewById(R.id.show_anim), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        final WindowManager.LayoutParams[] params = {getWindow().getAttributes()};
        //当弹出Popupwindow时，背景变半透明
        params[0].alpha = 0.7f;
        getWindow().setAttributes(params[0]);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        takePhotoPopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params[0] = getWindow().getAttributes();
                params[0].alpha = 1f;
                getWindow().setAttributes(params[0]);
            }
        });
    }

    private void initPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.activity_popupwidowlist, null);
        view_touming.setVisibility(View.VISIBLE);

        ListView listView = (ListView) view.findViewById(R.id.list_item);
        popupwindowAdapter popupwindowAdapter = new popupwindowAdapter(this, datas);
        listView.setAdapter(popupwindowAdapter);

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view_touming.setVisibility(View.GONE);
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(mShowPopupwindow, 0, 0);
    }

    @OnClick(R.id.show_popupwindow)
    public void goShowPopupwindow() {

    }

    @OnClick(R.id.show_anim)
    public void showAnim() {
//        mClientPhonePupopwindow.startAnimation(scaleAnimation);

        showPopFormBottom();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        return super.onTouchEvent(event);
    }
}
