package com.example.luoshuimumu.TopNews.gank.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * 共6种类型，以category为区分
 * Created by luoshuimumu on 2018/1/11.
 */

public class Content {
    private String id;
    private String category;
    @SerializedName("display_category")
    private int displayCategory;
    @SerializedName("item_id")
    private String itemId;
    private String title;
    private String forward;
    @SerializedName("img_url")
    private String imgUrl;
    @SerializedName("like_count")
    private int likeCount;
    @SerializedName("post_date")
    private Date postDate;
    @SerializedName("last_update_date")
    private Date lastUpdateDate;
    private Author author;
    @SerializedName("video_url")
    private String videoUrl;
    @SerializedName("audio_url")
    private String audioUrl;
    @SerializedName("audio_platform")
    private int audioPlatform;
    @SerializedName("start_video")
    private String startVideo;
    @SerializedName("has_reading")
    private int hasReading;
    private Date volume;
    @SerializedName("pic_info")
    private String picInfo;
    @SerializedName("words_info")
    private String wordsInfo;
    private String subtitle;
    private int number;
    @SerializedName("serial_id")
    private int serialId;
    @SerializedName("serial_list")
    private List<String> serialList;
    @SerializedName("movie_story_id")
    private int movieStoryId;
    @SerializedName("ad_id")
    private int adId;
    @SerializedName("ad_type")
    private int adType;
    @SerializedName("ad_pvurl")
    private String adPvurl;
    @SerializedName("ad_linkurl")
    private String adLinkurl;
    @SerializedName("ad_makettime")
    private String adMakettime;
    @SerializedName("ad_closetime")
    private String adClosetime;
    @SerializedName("ad_share_cnt")
    private String adShareCnt;
    @SerializedName("ad_pvurl_vendor")
    private String adPvurlVendor;
    @SerializedName("content_id")
    private String contentId;
    @SerializedName("content_type")
    private String contentType;
    @SerializedName("content_bgcolor")
    private String contentBgcolor;
    @SerializedName("share_url")
    private Date shareUrl;
    @SerializedName("share_info")
    private ShareInfo shareInfo;
    @SerializedName("share_list")
    private ShareList shareList;
    @SerializedName("tag_list")
    private List<String> tagList;
    private String orientation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDisplayCategory() {
        return displayCategory;
    }

    public void setDisplayCategory(int displayCategory) {
        this.displayCategory = displayCategory;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public int getAudioPlatform() {
        return audioPlatform;
    }

    public void setAudioPlatform(int audioPlatform) {
        this.audioPlatform = audioPlatform;
    }

    public String getStartVideo() {
        return startVideo;
    }

    public void setStartVideo(String startVideo) {
        this.startVideo = startVideo;
    }

    public int getHasReading() {
        return hasReading;
    }

    public void setHasReading(int hasReading) {
        this.hasReading = hasReading;
    }

    public Date getVolume() {
        return volume;
    }

    public void setVolume(Date volume) {
        this.volume = volume;
    }

    public String getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(String picInfo) {
        this.picInfo = picInfo;
    }

    public String getWordsInfo() {
        return wordsInfo;
    }

    public void setWordsInfo(String wordsInfo) {
        this.wordsInfo = wordsInfo;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSerialId() {
        return serialId;
    }

    public void setSerialId(int serialId) {
        this.serialId = serialId;
    }

    public List<String> getSerialList() {
        return serialList;
    }

    public void setSerialList(List<String> serialList) {
        this.serialList = serialList;
    }

    public int getMovieStoryId() {
        return movieStoryId;
    }

    public void setMovieStoryId(int movieStoryId) {
        this.movieStoryId = movieStoryId;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public String getAdPvurl() {
        return adPvurl;
    }

    public void setAdPvurl(String adPvurl) {
        this.adPvurl = adPvurl;
    }

    public String getAdLinkurl() {
        return adLinkurl;
    }

    public void setAdLinkurl(String adLinkurl) {
        this.adLinkurl = adLinkurl;
    }

    public String getAdMakettime() {
        return adMakettime;
    }

    public void setAdMakettime(String adMakettime) {
        this.adMakettime = adMakettime;
    }

    public String getAdClosetime() {
        return adClosetime;
    }

    public void setAdClosetime(String adClosetime) {
        this.adClosetime = adClosetime;
    }

    public String getAdShareCnt() {
        return adShareCnt;
    }

    public void setAdShareCnt(String adShareCnt) {
        this.adShareCnt = adShareCnt;
    }

    public String getAdPvurlVendor() {
        return adPvurlVendor;
    }

    public void setAdPvurlVendor(String adPvurlVendor) {
        this.adPvurlVendor = adPvurlVendor;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentBgcolor() {
        return contentBgcolor;
    }

    public void setContentBgcolor(String contentBgcolor) {
        this.contentBgcolor = contentBgcolor;
    }

    public Date getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(Date shareUrl) {
        this.shareUrl = shareUrl;
    }

    public ShareInfo getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public ShareList getShareList() {
        return shareList;
    }

    public void setShareList(ShareList shareList) {
        this.shareList = shareList;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
