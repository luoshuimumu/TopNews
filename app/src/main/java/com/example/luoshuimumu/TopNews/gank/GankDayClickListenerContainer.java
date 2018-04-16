package com.example.luoshuimumu.TopNews.gank;

import com.example.luoshuimumu.TopNews.widget.ListItemClickListenerMVVM;

/**
 * 为避免 vm等组件有太多set/get方法，将点击事件的监听器放到这里
 * Created by luoshuimumu on 2018/2/6.
 */

public class GankDayClickListenerContainer {
    private ListItemClickListenerMVVM<String> dayItemListener;

    public ListItemClickListenerMVVM<String> getDayItemListener() {
        return dayItemListener;
    }

    public void setDayItemListener(ListItemClickListenerMVVM<String> dayItemListener) {
        this.dayItemListener = dayItemListener;
    }
}
