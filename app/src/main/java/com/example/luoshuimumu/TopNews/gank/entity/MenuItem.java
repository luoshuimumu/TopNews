package com.example.luoshuimumu.TopNews.gank.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by luoshuimumu on 2018/1/11.
 */

public class MenuItem {
    @SerializedName("content_type")
    private String content_type;
    @SerializedName("content_id")
    private String contentId;
    @SerializedName("serial_list")
    private List<String> serialList;
    private String title;
    private Tag tag;

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public List<String> getSerialList() {
        return serialList;
    }

    public void setSerialList(List<String> serialList) {
        this.serialList = serialList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
