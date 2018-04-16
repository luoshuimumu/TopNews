package com.example.luoshuimumu.TopNews.gank.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luoshuimumu on 2017/11/9.
 */

public class News implements Serializable {
    String date;
    List<Stories> stories;
    @SerializedName("top_stories")
    List<TopStories> topStories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<TopStories> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStories> topStories) {
        this.topStories = topStories;
    }
}
