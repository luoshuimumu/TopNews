package com.example.luoshuimumu.TopNews.gank.vm;

import android.databinding.ObservableField;

/**
 * Created by luoshuimumu on 2018/5/4.
 */

public interface LoadingViewContainer {
    int STATUS_LOADING = 1;
    int STATUS_LOAD_FAIL = 2;
    int STATUS_LOAD_SUCCESS = 3;
    ObservableField<Integer> loadStatusCode = new ObservableField<>();
    ObservableField<String> loadStatusMsg = new ObservableField<>();
}
