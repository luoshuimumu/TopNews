package com.example.luoshuimumu.TopNews.gank.model;

import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;

import java.util.List;

/**
 * 存储gank数据
 * Created by luoshuimumu on 2018/4/12.
 */

public interface IGankStore {
    int storeGankDayHistory(List<String> data);

    int storeGankContent(GankContent data);

    List<GankContent> readGankContents(int count);

    String[] readGankDayHistory();
}
