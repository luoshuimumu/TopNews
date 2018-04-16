/**
 * Copyright 2018 bejson.com
 */
package com.example.luoshuimumu.TopNews.gank.entity;

import java.util.Date;

/**
 * Auto-generated: 2018-01-11 15:36:25
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ShareInfo {

    private Date url;
    private String image;
    private Date title;
    private String content;

    public void setUrl(Date url) {
        this.url = url;
    }

    public Date getUrl() {
        return url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(Date title) {
        this.title = title;
    }

    public Date getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}