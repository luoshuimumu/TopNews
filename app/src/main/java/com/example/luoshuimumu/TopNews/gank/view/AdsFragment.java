package com.example.luoshuimumu.TopNews.gank.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luoshuimumu.TopNews.R;
import com.example.luoshuimumu.TopNews.utils.CalendarUtil;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by luoshuimumu on 2018/5/4.
 */

public class AdsFragment extends RxDialogFragment {
    private int existTime = 3000;//毫秒


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable.empty().delay(existTime, TimeUnit.MILLISECONDS)
                .subscribe(o -> {
                    AdsFragment.this.dismiss();
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_gank_ads_layout, container);
        TextView tv = view.findViewById(R.id.aty_gank_ads_view_tv_today);
        tv.setText(String.format(getResources().getString(R.string.binder_today), getTodayString()));
        return view;
    }

    private String getTodayString() {
        return CalendarUtil.getYear() + "年" + CalendarUtil.getMonth() + "月" + CalendarUtil.getDayOfMonth() + "日";
    }
}
