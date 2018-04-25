/**
 *
 */
package com.example.luoshuimumu.TopNews.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 类说明：
 *
 * @author lizy
 * @version 1.0.0
 * @date 2015-8-5
 */
public class CalendarUtil {
    private static String TAG = CalendarUtil.class.getSimpleName();

    private int weeks = 0;// 用来全局控制 上一周，本周，下一周的周数变化
    private int MaxDate; // 一月最大天数
    private int MaxYear; // 一年最大天数


    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getDayOfYear() {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static int getWeekOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    public static Date getTimeYearNext() {
        Calendar.getInstance().add(Calendar.DAY_OF_YEAR, 183);
        return Calendar.getInstance().getTime();
    }

    public static String convertDateToString(Date dateTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
        return df.format(dateTime);
    }

    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException | NullPointerException e) {
            LogUtil.d(TAG, e.getStackTrace().toString());
            return "";
        }
        return Long.toString(day);
    }

    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = CalendarUtil.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    public static long getDays(String date1, String date2) {
        if (TextUtils.isEmpty(date1))
            return 0;
        if (TextUtils.isEmpty(date2)) {
            return 0;
        }
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (ParseException | NullPointerException e) {
            LogUtil.d(TAG, e.getStackTrace().toString());
        }
        if (date == null || mydate == null) {
            return 0;
        } else {
            return (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        }
    }

    public String getDefaultDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

        return sdf.format(lastDate.getTime());
    }

    public String getPreviousMonthFirst() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
        // lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

        return sdf.format(lastDate.getTime());
    }

    public String getFirstDayOfMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        return sdf.format(lastDate.getTime());
    }

    public String getCurrentWeekday() {
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();

        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    public String getNowTime(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat, Locale.SIMPLIFIED_CHINESE);// 可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        return hehe;
    }

    private int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public String getMondayOFWeek() {
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();

        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    public String getSaturday() {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    public String getPreviousWeekSunday() {
        weeks = 0;
        weeks--;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    public String getPreviousWeekday() {
        weeks--;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    public String getNextMonday() {
        weeks++;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    public String getNextSunday() {

        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    private int getMonthPlus() {
        Calendar cd = Calendar.getInstance();
        int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
        cd.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        cd.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        MaxDate = cd.get(Calendar.DATE);
        if (monthOfNumber == 1) {
            return -MaxDate;
        } else {
            return 1 - monthOfNumber;
        }
    }

    public String getPreviousMonthEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, -1);// 减一个月
        lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
        return sdf.format(lastDate.getTime());
    }

    public String getNextMonthFirst() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, 1);// 减一个月
        lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        return sdf.format(lastDate.getTime());
    }

    public String getNextMonthEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, 1);// 加一个月
        lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
        return sdf.format(lastDate.getTime());
    }

    public String getNextYearEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.YEAR, 1);// 加一个年
        lastDate.set(Calendar.DAY_OF_YEAR, 1);
        lastDate.roll(Calendar.DAY_OF_YEAR, -1);
        return sdf.format(lastDate.getTime());
    }

    public String getNextYearFirst() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);

        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.YEAR, 1);// 加一个年
        lastDate.set(Calendar.DAY_OF_YEAR, 1);
        return sdf.format(lastDate.getTime());

    }

    private int getMaxYear() {
        Calendar cd = Calendar.getInstance();
        cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
        cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
        return MaxYear;
    }

    private int getYearPlus() {
        Calendar cd = Calendar.getInstance();
        int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
        cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
        cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
        if (yearOfNumber == 1) {
            return -MaxYear;
        } else {
            return 1 - yearOfNumber;
        }
    }

    public String getCurrentYearFirst() {
        int yearPlus = this.getYearPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, yearPlus);
        Date yearDay = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preYearDay = df.format(yearDay);
        return preYearDay;
    }

    // 获得本年最后一天的日期 *
    public String getCurrentYearEnd() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.SIMPLIFIED_CHINESE);// 可以方便地修改日期格式
        String years = dateFormat.format(date);
        return years + "-12-31";
    }

    // 获得上年第一天的日期 *
    public String getPreviousYearFirst() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.SIMPLIFIED_CHINESE);// 可以方便地修改日期格式
        String years = dateFormat.format(date);
        int years_value = Integer.parseInt(years);
        years_value--;
        return years_value + "-1-1";
    }

    // 获得上年最后一天的日期
    public String getPreviousYearEnd() {
        weeks--;
        int yearPlus = this.getYearPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, yearPlus + MaxYear * weeks
                + (MaxYear - 1));
        Date yearDay = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preYearDay = df.format(yearDay);
        return preYearDay;
    }

    public String getThisSeasonFirstTime(int month) {
        int array[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int season = 1;
        if (month >= 1 && month <= 3) {
            season = 1;
        }
        if (month >= 4 && month <= 6) {
            season = 2;
        }
        if (month >= 7 && month <= 9) {
            season = 3;
        }
        if (month >= 10 && month <= 12) {
            season = 4;
        }
        int start_month = array[season - 1][0];
//        int end_month = array[season - 1][2];

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.SIMPLIFIED_CHINESE);// 可以方便地修改日期格式
        String years = dateFormat.format(date);
        int years_value = Integer.parseInt(years);

        int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
//        int end_days = getLastDayOfMonth(years_value, end_month);
        return years_value + "-" + start_month + "-" + start_days;
    }

    public String getThisSeasonFinallyTime(int month) {
        int array[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int season = 1;
        if (month >= 1 && month <= 3) {
            season = 1;
        }
        if (month >= 4 && month <= 6) {
            season = 2;
        }
        if (month >= 7 && month <= 9) {
            season = 3;
        }
        if (month >= 10 && month <= 12) {
            season = 4;
        }
//        int start_month = array[season - 1][0];
        int end_month = array[season - 1][2];

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.SIMPLIFIED_CHINESE);// 可以方便地修改日期格式
        String years = dateFormat.format(date);
        int years_value = Integer.parseInt(years);

//        int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
        int end_days = getLastDayOfMonth(years_value, end_month);
        return years_value + "-" + end_month + "-" + end_days;

    }

    private int getLastDayOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
    }

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public boolean isLeapYear2(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatSuggestDateString(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");// 可以方便地修改日期格式
            Date date = dateFormat.parse(dateStr);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");// 可以方便地修改日期格式
            return dateFormat2.format(date);
        } catch (IllegalArgumentException | ParseException | NullPointerException e) {
            LogUtil.d(TAG, e.getStackTrace().toString());
        }
        return "";
    }

    /**
     * 转换时间
     *
     * @param sendTime 格式为 "20161026142300"
     * @return
     */
    public static String getTime(String sendTime) {

        if (sendTime == null) {
            return "";
        }

        try {

            if (sendTime.length() >= 12) {   //14位的格式
                String year = sendTime.substring(0, 4);
                String month = sendTime.substring(4, 6);
                String day = sendTime.substring(6, 8);
                String hour = sendTime.substring(8, 10);
                String minute = sendTime.substring(10, 12);
                long current = System.currentTimeMillis();
                if (month != null && month.startsWith("0") && month.length() >= 2) {
                    month = month.substring(1, month.length());
                }
                if (day != null && day.startsWith("0") && day.length() >= 2) {
                    day = day.substring(1, day.length());
                }
                java.sql.Date time = new java.sql.Date(current);
                SimpleDateFormat yyyy = new SimpleDateFormat("yyyy", Locale.SIMPLIFIED_CHINESE);
                SimpleDateFormat MM = new SimpleDateFormat("MM", Locale.SIMPLIFIED_CHINESE);
                SimpleDateFormat dd = new SimpleDateFormat("dd", Locale.SIMPLIFIED_CHINESE);
                if (Integer.parseInt(year) < Integer.parseInt(yyyy.format(time))) {
                    return (year.substring(2, 4) + "年" + month + "月" + day + "日");
                } else if (Integer.parseInt(month) < Integer.parseInt(MM.format(time))
                        || (Integer.parseInt(dd.format(time)) - Integer.parseInt(day) > 1)) {
                    return (month + "月" + day + "日");
                } else if (Integer.parseInt(dd.format(time)) - Integer.parseInt(day) == 1) {
                    return ("昨天");
                } else {
                    return ("今天 " + hour + ":" + minute);
                }
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            LogUtil.d(TAG, e.getStackTrace().toString());
        }
        return "";
    }
}
