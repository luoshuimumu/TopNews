package com.example.luoshuimumu.TopNews.gank.model;

import android.content.Context;

import com.example.luoshuimumu.TopNews.base.BaseModel;
import com.example.luoshuimumu.TopNews.db.GankDataWriter;
import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;

import java.util.List;

/**
 * 存储Gank数据的model
 * Created by luoshuimumu on 2018/4/12.
 */

public class GankStoreModel extends BaseModel implements IGankStore {

    public GankStoreModel(Context context) {
        super(context);
    }

    @Override
    public int storeGankDayHistory(List<String> data) {
        GankDataWriter.updateGankDay(mContext, data);
        return 0;
    }

    @Override
    public int storeGankContent(GankContent data) {
        return 0;
    }

    @Override
    public List<GankContent> readGankContents(int count) {
        return null;
    }

    @Override
    public String[] readGankDayHistory() {
        return new String[0];
    }
}
