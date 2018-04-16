package com.example.luoshuimumu.TopNews.widget.annotation;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.luoshuimumu.TopNews.utils.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luoshuimumu on 2018/2/9.
 */

public class TextBinder {
    private final static String TAG = TextBinder.class.getName();

    @BindingAdapter(value = {"date", "format"}, requireAll = false)
    public static void DateBinder(TextView textView, String strDate, String strFormat) {
        //TODO 2018-02-08T08:13:24.479Z 解析
        if (TextUtils.isEmpty(strFormat)) {
            strFormat = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        try {
            Date date = format.parse(strDate);
            format = new SimpleDateFormat(strFormat);
            textView.setText(format.format(date));
        } catch (ParseException e) {
            textView.setText(strDate);
            LogUtil.e(TAG, e.getStackTrace().toString());
        }
    }

}
