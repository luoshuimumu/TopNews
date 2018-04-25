package com.example.luoshuimumu.TopNews.utils;

import android.util.Log;

/**
 * Created by luoshuimumu on 2017/11/13.
 */

public class LogUtil {
    private static boolean isDebug = true;
    private static String TAG = "lsmm";

    public static void d(String tag, String msg) {
        //TODO modify
        if (isDebug) {
            Log.d(tag, msg);
            System.out.println(tag + ":" + msg);
        }
    }

    public static void d(String msg) {
        //TODO modify
        if (isDebug) {
            Log.d(TAG, msg);
            System.out.println(TAG + ":" + msg);
        }
    }

    public static void e(String tag, String msg) {
        //TODO modify
        if (isDebug) {
            Log.e(tag, msg);
            System.out.println(tag + ":" + msg);
        }
    }

    public static void e(String msg) {
        //TODO modify
        if (isDebug) {
            Log.e(TAG,msg);
            System.out.println(msg);
        }
    }

    public static void e(Throwable e) {
        if (isDebug) {
            Log.e(TAG, e.getStackTrace().toString());
            e.printStackTrace();
        }
    }

}
