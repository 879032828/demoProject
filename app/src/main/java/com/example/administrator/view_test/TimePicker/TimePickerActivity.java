package com.example.administrator.view_test.TimePicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.administrator.view_test.DateUtil.DateUtils;
import com.example.administrator.view_test.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class TimePickerActivity extends Activity implements View.OnClickListener{

    public static final int RESULT_OK = 1;
    private String mCurrentTime;

    private TimePicker mTimePicker;
    private TextView mOk;
    private TextView mCancel;

    public static final String  START_OR_END = "start_or_end";
    private int mStartFlag = 0;
    private int mEndFlag = 1;
    private int mStartOrEnd = -1;

    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    private String mStartTime;
    private String mEndTime;

    private int mStartTimeHours;
    private int mStartTimeMinutes;
    private int mEndTimeHours;
    private int mEndTimeMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
        getIntentData();
        initView();
    }


    /**
     * 分别获取开始时间mStartTime和结束时间mEndTime的字符串
     * 并由mStartOrEnd判断是当前是开始时间还是结束时间
     * @return
     */
    private String getIntentData(){
        Intent intent = getIntent();
        mStartTime = intent.getStringExtra(START_TIME);
        mEndTime = intent.getStringExtra(END_TIME);
        mStartOrEnd = intent.getIntExtra(START_OR_END, -1);

        mStartTimeHours = DateUtils.getHoursByStr(mStartTime);
        mStartTimeMinutes = DateUtils.getMinutesByStr(mStartTime);

        mEndTimeHours = DateUtils.getHoursByStr(mEndTime);
        mEndTimeMinutes = DateUtils.getMinutesByStr(mEndTime);

        return null;
    }

    private void initView() {
        mCurrentTime = "";

        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        mOk = (TextView) findViewById(R.id.ok);
        mOk.setOnClickListener(this);
        mCancel = (TextView) findViewById(R.id.cancel);
        mCancel.setOnClickListener(this);

        setCurrentTime();
        initTimePicker();
    }

    private void initTimePicker(){
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mCurrentTime = "" + hourOfDay + ":" + String.format("%02d", minute);
            }
        });

    }

    /**
     * 比较开始时间和结束时间
     * @return
     */
    private boolean compareTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        switch (mStartOrEnd){
            case 0 :
                try {
                    Date starttime = sdf.parse(mCurrentTime);
                    Date endtime = sdf.parse(mEndTime);
                    if (starttime.getTime() >= endtime.getTime()){
                        Toast.makeText(this,"开始时间大于结束时间", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    return true;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 1 :
                try {
                    Date starttime = sdf.parse(mStartTime);
                    Date endtime = sdf.parse(mCurrentTime);
                    if (starttime.getTime() >= endtime.getTime()){
                        Toast.makeText(this,"结束时间小于开始时间", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    return true;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
        return false;
    }

    private void setCurrentTime(){
        if (mStartOrEnd == mStartFlag){
            mTimePicker.setCurrentHour(mStartTimeHours);
            mTimePicker.setCurrentMinute(mStartTimeMinutes);
        }else {
            mTimePicker.setCurrentHour(mEndTimeHours);
            mTimePicker.setCurrentMinute(mEndTimeMinutes);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok :
                if (compareTime()){
                    Intent intent = new Intent();
                    intent.putExtra("time", mCurrentTime);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.cancel :
                finish();
        }
    }
}
