package com.example.luoshuimumu.TopNews.utils;

/**
 * Created by luoshuimumu on 2017/11/13.
 */

public class LogUtil {
    private static boolean isDebug = true;

    public static void d(String tag, String msg) {
        //TODO modify
    }

    public static void d(String msg) {
        //TODO modify
    }

    public static void e(String tag, String msg) {
        //TODO modify
        System.out.println(tag + ":" + msg);
    }

    public static void e(String msg) {
        //TODO modify
        if (isDebug) {
            System.out.println(msg);
        }
    }

    public static void e(Throwable e) {
        if (isDebug) {
            e.printStackTrace();
        }
    }

}
