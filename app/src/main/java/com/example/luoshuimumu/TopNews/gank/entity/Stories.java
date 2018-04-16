package com.example.luoshuimumu.TopNews.gank.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by luoshuimumu on 2017/12/20.
 */

public class Stories {
    List<String> images;
    int type;
    long id;
    @SerializedName("ga_prefix")
    String gaPrefix;
    String title;
    boolean multipic;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }
}
