/**
 * Copyright 2018 bejson.com
 */
package com.example.luoshuimumu.TopNews.gank.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Auto-generated: 2018-01-11 15:36:25
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ShareList {

    private Wx wx;
    @SerializedName("wx_timeline")
    private WxTimeline wxTimeline;
    private Weibo weibo;
    private Qq qq;

    public Wx getWx() {
        return wx;
    }

    public void setWx(Wx wx) {
        this.wx = wx;
    }

    public WxTimeline getWxTimeline() {
        return wxTimeline;
    }

    public void setWxTimeline(WxTimeline wxTimeline) {
        this.wxTimeline = wxTimeline;
    }

    public Weibo getWeibo() {
        return weibo;
    }

    public void setWeibo(Weibo weibo) {
        this.weibo = weibo;
    }

    public Qq getQq() {
        return qq;
    }

    public void setQq(Qq qq) {
        this.qq = qq;
    }
}