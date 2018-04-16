package com.example.luoshuimumu.TopNews.gank.entity.response;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.luoshuimumu.TopNews.gank.entity.Content;
import com.example.luoshuimumu.TopNews.gank.entity.Menu;
import com.example.luoshuimumu.TopNews.gank.entity.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by luoshuimumu on 2018/1/11.
 */

public class OnelistResp extends BaseObservable {
    String id;
    List<Weather> weather;
    Date date;
    @SerializedName("content_list")
    List<Content> contentList;
    List<Menu> menu;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Bindable
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Bindable
    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    @Bindable
    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
}
