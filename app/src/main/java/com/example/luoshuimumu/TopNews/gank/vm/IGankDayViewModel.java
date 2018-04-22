package com.example.luoshuimumu.TopNews.gank.vm;

import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;

/**
 * Created by luoshuimumu on 2018/4/12.
 */

public interface IGankDayViewModel {
    void getDayHistory();

    void onDaySelected(String date);

    void onContentSelected(GankContent gankContent);
}
