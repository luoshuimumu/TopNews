package com.example.luoshuimumu.TopNews.gank.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luoshuimumu on 2017/12/20.
 */

public class TopStories {
    String image;
    int type;
    long id;
    @SerializedName("ga_prefix")
    String gaPrefix;
    String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
