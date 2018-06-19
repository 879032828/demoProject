package com.example.administrator.view_test.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.view_test.Base.BaseActivity;
import com.example.administrator.view_test.MyApplication;
import com.example.administrator.view_test.R;

import butterknife.BindView;
import butterknife.OnClick;

public class EventTestActivity extends BaseActivity {

    @BindView(R.id.show_event)
    public Button mShowEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_test;
    }

    @Override
    public void initPresenter() {

    }

    @OnClick(R.id.show_event)
    public void clickToShowEvent() {
        UIEvent event = new UIEvent();
        event.setType(UIEventType.ClearChatLog);
        UIEventManager.getInstance().broadcast(event);
        Log.d(MyApplication.EVENT_DEBUG, "clickToShowEvent");
    }

    @UIEventHandler(UIEventType.ClearChatLog)
    public void showEvent(UIEvent uiEvent) {
//        Log.d(MyApplication.EVENT_DEBUG, "showEvent");
//        Toast.makeText(this, this.getClass() + "UIEventHandler 被点击了！！！", Toast.LENGTH_SHORT).show();
    }
}
