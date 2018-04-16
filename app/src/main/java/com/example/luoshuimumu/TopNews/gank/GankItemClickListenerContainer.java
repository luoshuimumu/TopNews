package com.example.luoshuimumu.TopNews.gank;

import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;
import com.example.luoshuimumu.TopNews.widget.ListItemClickListenerMVVM;
import com.example.luoshuimumu.TopNews.widget.ListItemLongClickListenerMVVM;

/**
 * 为避免 vm等组件有太多set/get方法，将点击事件的监听器放到这里
 * Created by luoshuimumu on 2018/2/6.
 */

public class GankItemClickListenerContainer {
    private ListItemClickListenerMVVM<GankContent> contentListener;

    /***********        公有监听        *************/
    private ListItemClickListenerMVVM<GankContent> likeListener;
    private ListItemClickListenerMVVM<GankContent> shareListener;
    private ListItemLongClickListenerMVVM<GankContent> longClickListener;

    public ListItemClickListenerMVVM<GankContent> getContentListener() {
        return contentListener;
    }

    public void setContentListener(ListItemClickListenerMVVM<GankContent> contentListener) {
        this.contentListener = contentListener;
    }

    public ListItemClickListenerMVVM<GankContent> getLikeListener() {
        return likeListener;
    }

    public void setLikeListener(ListItemClickListenerMVVM<GankContent> likeListener) {
        this.likeListener = likeListener;
    }

    public ListItemClickListenerMVVM<GankContent> getShareListener() {
        return shareListener;
    }

    public void setShareListener(ListItemClickListenerMVVM<GankContent> shareListener) {
        this.shareListener = shareListener;
    }

    public ListItemLongClickListenerMVVM<GankContent> getLongClickListener() {
        return longClickListener;
    }

    public void setLongClickListener(ListItemLongClickListenerMVVM<GankContent> longClickListener) {
        this.longClickListener = longClickListener;
    }
}
