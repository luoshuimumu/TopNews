package com.example.luoshuimumu.TopNews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.luoshuimumu.TopNews.utils.LogUtil;

/**
 * 此类应该持有数据相关到的各个信息
 * 只负责数据库建立
 * Created by luoshuimumu on 2018/2/26.
 */

public class GankDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "gank.db";

    public static final String TABLE_NAME_GANK_DAY = "gank_day";//发布gank的历史日期表
    public static final String TABLE_NAME_GANK_CONTENT = "gank_content";//包含某日详细的数据
    private static final String TAG = GankDbHelper.class.getSimpleName();


    public GankDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //获取版本号
//        DB_VERSION = VersionCodeUtil.getVersionCode(context);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        LogUtil.d(TAG, "onCreate");
        database.execSQL("CREATE TABLE  IF NOT EXISTS " + TABLE_NAME_GANK_DAY
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + " day varchar(128))");
        database.execSQL("CREATE TABLE  IF NOT EXISTS " + TABLE_NAME_GANK_CONTENT
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + " name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        LogUtil.d(TAG, "onUpgrade");
        //TODO 更新db 增删列等

    }
}
