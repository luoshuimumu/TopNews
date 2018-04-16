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
public class Author {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("user_name")
    private String userName;
    private String desc;
    @SerializedName("wb_name")
    private String wbName;
    @SerializedName("is_settled")
    private String isSettled;
    @SerializedName("settled_type")
    private String settledType;
    private String summary;
    @SerializedName("fans_total")
    private String fansTotal;
    @SerializedName("web_url")
    private String webUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWbName() {
        return wbName;
    }

    public void setWbName(String wbName) {
        this.wbName = wbName;
    }

    public String getIsSettled() {
        return isSettled;
    }

    public void setIsSettled(String isSettled) {
        this.isSettled = isSettled;
    }

    public String getSettledType() {
        return settledType;
    }

    public void setSettledType(String settledType) {
        this.settledType = settledType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFansTotal() {
        return fansTotal;
    }

    public void setFansTotal(String fansTotal) {
        this.fansTotal = fansTotal;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}