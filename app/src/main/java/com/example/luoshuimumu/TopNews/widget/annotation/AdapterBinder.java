package com.example.luoshuimumu.TopNews.widget.annotation;

import android.databinding.BindingAdapter;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.luoshuimumu.TopNews.utils.ScreenUtils;

/**
 * 直接用android:adapter不行
 * Created by luoshuimumu on 2018/2/4.
 */

public class AdapterBinder {
    @BindingAdapter(value = {"adapter", "layoutManager"}, requireAll = false)
    public static void bindAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter
            , RecyclerView.LayoutManager layoutManager
    ) {
        if (null != recyclerView && null != adapter) {
            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.bottom = ScreenUtils.dpToPx(8);
                }
            });
            if (null == layoutManager) {
                layoutManager = new LinearLayoutManager(recyclerView.getContext());
                ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
            }
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
