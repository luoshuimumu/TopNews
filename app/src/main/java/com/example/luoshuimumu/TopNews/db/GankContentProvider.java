package com.example.luoshuimumu.TopNews.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.luoshuimumu.TopNews.utils.LogUtil;
import com.example.luoshuimumu.TopNews.utils.VersionCodeUtil;

import static com.example.luoshuimumu.TopNews.db.GankDbHelper.TABLE_NAME_GANK_DAY;

/**
 * todo 防止恶意sql
 * Created by luoshuimumu on 2018/1/12.
 */

public class GankContentProvider extends ContentProvider {
    private String TAG = GankContentProvider.class.getName();
    public static final String AUTHORITY = "com.example.luoshuimumu.TopNews";
    public static final String PREFIX_DIR = "vnd.android.cursor.dir/";
    public static final String PREFIX_ITEM = "vnd.android.cursor.item/";

    private final static int URI_CODE_GANK_DAY = 1;

    GankDbHelper mHelper = null;
    SQLiteDatabase db = null;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //鉴权 authority 如果匹配到 TABLE_NAME_GANK_DAY ，就返回 URI_CODE_GANK_DAY
        uriMatcher.addURI(AUTHORITY, GankDbHelper.TABLE_NAME_GANK_DAY, URI_CODE_GANK_DAY);
    }

    @Override
    public boolean onCreate() {
        LogUtil.d(TAG, "onCreate");
        mHelper = new GankDbHelper(getContext(), GankDbHelper.DB_NAME, null,
                VersionCodeUtil.getVersionCode(getContext()));
        db = mHelper.getWritableDatabase();
        return true;
    }

    /**
     * 返回响应的
     *
     * @param uri
     * @return A/B类型 匹配 manifest 下 activity 的 intent-filter 的 data 的 android:mimeType，不一致会导致 activity 无法启动
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int code = uriMatcher.match(uri);
        String result = null;
        switch (code) {
            case URI_CODE_GANK_DAY:
                result = PREFIX_DIR;
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getTableName(uri);
        LogUtil.d(TAG, "query table " + table);
        if (!TextUtils.isEmpty(table)) {
            return db.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
        }
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //TODO 插入
        //根据uri获取表名，插入
        String table = getTableName(uri);
        LogUtil.d(TAG, "insert table " + table + ",values " + (null != values ? values.toString() : "null"));
        long insertResult = 0;
        if (!TextUtils.isEmpty(table)) {
            insertResult = db.insert(table, null, values);
            //通知观察者
            ContentResolver resolver = getContext().getContentResolver();
            if (null != resolver) {
                resolver.notifyChange(uri, null);
            }
        }
        LogUtil.d(TAG, "insertResult:" + insertResult);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        LogUtil.d(TAG, "delete table " + table + " by " + selection);
        int deleteCount = 0;
        if (!TextUtils.isEmpty(table)) {
            deleteCount = db.delete(table, selection, selectionArgs);
        }
        if (deleteCount > 0) {
            ContentResolver resolver = getContext().getContentResolver();
            if (null != resolver) {
                resolver.notifyChange(uri, null);
            }
        }
        LogUtil.d(TAG, "delete result " + deleteCount);
        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        LogUtil.d(TAG, "update table " + table + ",values" + (null != values ? values.toString() : "null"));
        int updateCount = 0;
        if (!TextUtils.isEmpty(table)) {
            updateCount = db.update(table, values, selection, selectionArgs);
        }
        LogUtil.d(TAG, "delete result " + updateCount);
        return 0;
    }

    private String getTableName(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_CODE_GANK_DAY:
                LogUtil.d(TAG, "getTableName:" + TABLE_NAME_GANK_DAY);
                return TABLE_NAME_GANK_DAY;
            default:
                break;
        }
        LogUtil.d(TAG, "getTableName:" + null);
        return null;
    }


}
