package com.example.luoshuimumu.TopNews.gank.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.luoshuimumu.TopNews.gank.GankDayClickListenerContainer;

/**
 * Created by luoshuimumu on 2018/2/5.
 */

public class BaseGankDayVM extends BaseObservable {
    @Bindable
    private String imgUrl;
    @Bindable
    private String day;
    @Bindable
    private boolean isSelected;//该项目是否被选中，用于更改ui

    @Bindable
    GankDayClickListenerContainer listenerContainer = new GankDayClickListenerContainer();

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String data) {
        this.day = data;
    }

    public GankDayClickListenerContainer getListenerContainer() {
        return listenerContainer;
    }

    public void setListenerContainer(GankDayClickListenerContainer listenerContainer) {
        this.listenerContainer = listenerContainer;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}