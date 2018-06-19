package com.example.administrator.view_test.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/12 0012.
 */

public class DateUtils {

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * @param time 格式为“20:12” 即时分格式
     * @return 返回值为int型的时钟
     */
    public static int getHoursByStr(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        try {
            Date dtime = sdf.parse(time);
            return dtime.getHours();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @param time 格式为“20:12” 即时分格式
     * @return 返回值为int型的分钟
     */
    public static int getMinutesByStr(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        try {
            Date dtime = sdf.parse(time);
            return dtime.getMinutes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
