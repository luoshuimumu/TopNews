package com.example.luoshuimumu.TopNews.widget.annotation;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.luoshuimumu.TopNews.utils.CalendarUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luoshuimumu on 2018/2/9.
 */

public class TextBinder {
    private final static String TAG = TextBinder.class.getName();

    @BindingAdapter(value = {"date", "format"}, requireAll = false)
    public static void DateBinder(TextView textView, Date date, String strFormat) {
        if (null == date) {
            return;
        }
        if (TextUtils.isEmpty(strFormat)) {
            strFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        String dayStr = format.format(date);
        //解析特有字符串 比如今天/昨天/三天前
        int yearCurrent = CalendarUtil.getYear();
        int monthCurrent = CalendarUtil.getMonth();
        int dayCurrent = CalendarUtil.getDayOfMonth();

        String[] dayArr = dayStr.split("-");
        if (null != dayArr && dayArr.length >= 3) {
            String yearContent = dayArr[0];
            String monthContent = dayArr[1];
            String dayContent = dayArr[2];
            dayStr = yearContent + "年" + monthContent + "月" + dayContent + "日";
            if (yearContent.equals(String.valueOf(yearCurrent))) {
                dayStr = monthContent + "-" + dayContent;
                if (monthContent.equals(String.valueOf(monthCurrent))) {
                    if (dayContent.equals(String.valueOf(dayCurrent))) {
                        dayStr = "今天";
                    } else if (dayContent.equals(String.valueOf(dayCurrent + 1))) {
                        dayStr = "昨天";
                    } else if (dayContent.equals(String.valueOf(dayCurrent + 1))) {
                        dayStr = "两天前";
                    }
                }
            }
        }
        textView.setText(dayStr);
    }
}
