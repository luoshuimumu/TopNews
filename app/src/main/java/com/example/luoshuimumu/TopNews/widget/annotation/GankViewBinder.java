package com.example.luoshuimumu.TopNews.widget.annotation;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.luoshuimumu.TopNews.gank.widget.GankContentListAdapter;
import com.example.luoshuimumu.TopNews.gank.widget.GankDayListAdapter;
import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;

import java.util.List;


/**
 * Created by luoshuimumu on 2018/2/5.
 */

/**
 * gank网站的图片需要按宽度获取
 */
public class GankViewBinder {
    @BindingAdapter(value = {"gankDayData"}, requireAll = false)
    public static void setGankDayData(RecyclerView recyclerView, List<String> data) {
        GankDayListAdapter adapter =
                (GankDayListAdapter) recyclerView.getAdapter();
        adapter.setData(data);
    }

    @BindingAdapter(value = {"gankContentData"}, requireAll = false)
    public static void setGankContentData(RecyclerView recyclerView, List<GankContent> data) {
        GankContentListAdapter adapter =
                (GankContentListAdapter) recyclerView.getAdapter();
        adapter.setData(data);
    }
}
