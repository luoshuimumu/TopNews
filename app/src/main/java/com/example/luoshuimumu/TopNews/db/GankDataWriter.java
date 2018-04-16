package com.example.luoshuimumu.TopNews.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 历史数据一般是不会更改的 于是可以写入数据库
 * Created by luoshuimumu on 2018/2/26.
 */

public class GankDataWriter {
    private static final String TAG = GankDataWriter.class.getSimpleName();
    private static Uri gankDayUri = Uri.parse("content://"
            + GankContentProvider.AUTHORITY + "/" + GankDbHelper.TABLE_NAME_GANK_DAY);

    private static Uri gankContentUri = Uri.parse("content://"
            + GankContentProvider.AUTHORITY + "/" + GankDbHelper.TABLE_NAME_GANK_CONTENT);


    private static String imagesDelimiter = ",";

    /**
     * TODO 将数据写入数据库
     */
    public static void writeGankContent(Context context, GankContent gankContent) {
        ContentValues values = new ContentValues();
        values.put("_id", gankContent.get_id());

        String createdAtStr = null;
        Date createdAt = gankContent.getCreatedAt();
        if (null != createdAt) {
            createdAtStr = createdAt.toString();
        }
        values.put("createdAt", createdAtStr);

        values.put("desc", gankContent.getDesc());
        StringBuffer imagesStr = new StringBuffer();
        List<String> images = gankContent.getImages();
        if (null != images && 0 < images.size()) {
            for (String image : images) {
                if (!TextUtils.isEmpty(image)) {
                    imagesStr.append(image + imagesDelimiter);
                }
            }
            if (!TextUtils.isEmpty(imagesStr)) {
//                TODO go on
//                imagesStr.substring()
//                imagesStr = imagesStr.toString().substring(imagesStr.toString().length() - 1);
            }
        }
        values.put("_id", gankContent.get_id());
        values.put("_id", gankContent.get_id());
        values.put("_id", gankContent.get_id());
        values.put("_id", gankContent.get_id());
        values.put("_id", gankContent.get_id());


        if (null != context) {
            ContentResolver resolver =
                    context.getContentResolver();
            if (null != resolver) {
                resolver.insert(gankContentUri, values);
//                LogUtil.d(TAG, "insert data : " + day);
            }
        }
    }

    /**
     * TODO 通过发布日期查询数据
     *
     * @param day
     * @return
     */
    public static GankContent queryGankContent(Context context, String day) {
        if (null != context) {
            ContentResolver resolver =
                    context.getContentResolver();
            if (null != resolver) {
                Cursor cursor = resolver.query(gankContentUri, null, "day=?", new String[]{
                        day
                }, null);
                if (null != cursor) {
                    cursor.moveToNext();
                    String _id = cursor.getString(0);
                    String createdAtStr = cursor.getString(1);
                    String desc = cursor.getString(2);
                    String imagesStr = cursor.getString(3);//TODO ,分隔
                    String publishedAtStr = cursor.getString(4);
                    String source = cursor.getString(5);
                    String type = cursor.getString(6);
                    String url = cursor.getString(7);
                    int usedInt = cursor.getInt(8);//TODO 1-true 0-false
                    String who = cursor.getString(9);

                    List<String> images = TextUtils.isEmpty(imagesStr) ? null :
                            Arrays.asList(imagesStr.split(imagesDelimiter));
                    boolean used = usedInt == 0 ? false : true;
                    SimpleDateFormat format = new SimpleDateFormat();
                    Date createdAt = null;
                    Date publishedAt = null;
                    try {
                        createdAt = format.parse(createdAtStr);
                        publishedAt = format.parse(publishedAtStr);
                    } catch (ParseException e) {
                        Log.e(TAG, "parse date error when parse data which id=" + _id + " and" +
                                "createdAt=" + createdAtStr + " and publishedAt=" + publishedAtStr + ".");
                        Log.e(TAG, e.getStackTrace().toString());
                    }

                    GankContent gankContent = new GankContent();
                    gankContent.set_id(_id);
                    gankContent.setCreatedAt(createdAt);
                    gankContent.setDesc(desc);
                    gankContent.setImages(images);
                    gankContent.setPublishedAt(publishedAt);
                    gankContent.setSource(source);
                    gankContent.setType(type);
                    gankContent.setUrl(url);
                    gankContent.setUsed(used);
                    gankContent.setWho(who);

                    cursor.close();
                    return gankContent;
                }
            }
        }
        return null;
    }

    /**
     * TODO 根据日期删除无效的数据
     *
     * @param context
     * @param day
     */
    public static void deleteGankContent(Context context, String day) {
        if (null != context) {
            ContentResolver resolver =
                    context.getContentResolver();
            if (null != resolver) {
                resolver.delete(gankContentUri, "day=?", new String[]{day});
            }
        }
    }

    /**
     * 删除原有的day表 重建新的表
     *
     * @param context
     * @param dayList
     */
    public static void updateGankDay(Context context, List<String> dayList) {
        Observable.create((emmiter) -> {

        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
        ;
        if (null != context) {
            ContentResolver resolver =
                    context.getContentResolver();
            if (null != resolver) {
                //append all dayList
                if (null != dayList && 0 < dayList.size()) {
                    resolver.delete(gankDayUri, null, null);
                    for (String day : dayList) {
                        ContentValues values = new ContentValues();
                        values.put("day", day);
                        //TODO 单独插入效率很低啊
                        resolver.insert(gankDayUri, values);
                    }
                }
            }
        }
    }

}
