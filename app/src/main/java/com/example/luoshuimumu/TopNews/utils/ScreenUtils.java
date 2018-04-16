package com.example.luoshuimumu.TopNews.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class ScreenUtils {

    public static Resources getResources(Context context) {
        if (context == null) {
            return Resources.getSystem();
        } else {
            return context.getResources();
        }
    }

    public static int dpToPx(float dpValue) {
        return dpToPx(null, dpValue);
    }

    public static int pxToSp(float dpValue) {
        return pxToSp(null, dpValue);
    }

    public static int dpToPxWithTypeValue(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources(null).getDisplayMetrics());
    }

    public static int spToPxWithTypeValue(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dpValue, getResources(null).getDisplayMetrics());
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)【参数Context】
     */
    public static int dpToPx(Context context, float dpValue) {
        Resources resources = getResources(context);
        final float scale = resources.getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp【参数Context】
     */
    public static int pxToDp(Context context, float pxValue) {
        Resources resources = getResources(context);
        final float scale = resources.getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)【参数Resources】
     */
    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                res.getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp【参数Context】
     */
    public static int pxToSp(Context context, float pxValue) {
        Resources resources = getResources(context);
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px(像素)【参数Resources】
     */
    public static int spToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp,
                res.getDisplayMetrics());
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}
