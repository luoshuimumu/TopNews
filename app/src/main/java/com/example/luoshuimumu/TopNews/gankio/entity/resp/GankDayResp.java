package com.example.luoshuimumu.TopNews.gankio.entity.resp;

import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;

import java.util.ArrayList;
import java.util.List;

/**
 * api返回的key并不是固定的 只能做成树状结构，
 * key仅当展示用 具体类型用有无关键数据来区分类型
 * Created by luoshuimumu on 2018/2/4.
 */

public class GankDayResp {
    private List<GankContent> data = new ArrayList<>();

    public List<GankContent> getData() {
        return data;
    }

    public void setData(List<GankContent> data) {
        this.data = data;
    }
    //    @SerializedName(ContentCategory.ValueRestVideo)
//    private List<GankContent> restVideo;
//    @SerializedName(ContentCategory.ValueFrontEnd)
//    private List<GankContent> frontEnd;
//    @SerializedName(ContentCategory.ValueIos)
//    private List<GankContent> ios;
//    @SerializedName(ContentCategory.ValueExtendResource)
//    private List<GankContent> extendsResource;
//    @SerializedName(ContentCategory.ValueAndroid)
//    private List<GankContent> android;
//    @SerializedName(ContentCategory.ValueWelfare)
//    private List<GankContent> welfare;
//
//    public List<GankContent> getRestVideo() {
//        return restVideo;
//    }
//
//    public void setRestVideo(List<GankContent> restVideo) {
//        this.restVideo = restVideo;
//    }
//
//    public List<GankContent> getFrontEnd() {
//        return frontEnd;
//    }
//
//    public void setFrontEnd(List<GankContent> frontEnd) {
//        this.frontEnd = frontEnd;
//    }
//
//    public List<GankContent> getIos() {
//        return ios;
//    }
//
//    public void setIos(List<GankContent> ios) {
//        this.ios = ios;
//    }
//
//    public List<GankContent> getExtendsResource() {
//        return extendsResource;
//    }
//
//    public void setExtendsResource(List<GankContent> extendsResource) {
//        this.extendsResource = extendsResource;
//    }
//
//    public List<GankContent> getAndroid() {
//        return android;
//    }
//
//    public void setAndroid(List<GankContent> android) {
//        this.android = android;
//    }
//
//    public List<GankContent> getWelfare() {
//        return welfare;
//    }
//
//    public void setWelfare(List<GankContent> welfare) {
//        this.welfare = welfare;
//    }
}
