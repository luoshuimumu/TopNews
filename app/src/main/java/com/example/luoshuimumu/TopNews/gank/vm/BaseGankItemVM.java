package com.example.luoshuimumu.TopNews.gank.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.luoshuimumu.TopNews.gank.GankItemClickListenerContainer;
import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;

/**
 * Created by luoshuimumu on 2018/2/5.
 */

public class BaseGankItemVM extends BaseObservable {
    @Bindable
    private GankContent data;
    @Bindable
    GankItemClickListenerContainer listenerContainer = new GankItemClickListenerContainer();

    public GankContent getData() {
        return data;
    }

    public void setData(GankContent data) {
        this.data = data;
    }

    public GankItemClickListenerContainer getListenerContainer() {
        return listenerContainer;
    }

    public void setListenerContainer(GankItemClickListenerContainer listenerContainer) {
        this.listenerContainer = listenerContainer;
    }
}