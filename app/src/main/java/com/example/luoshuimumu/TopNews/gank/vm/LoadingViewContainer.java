package com.example.luoshuimumu.TopNews.gank.vm;

import android.databinding.ObservableField;

/**
 * Created by luoshuimumu on 2018/5/4.
 */

public class LoadingViewContainer {
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_LOAD_FAIL = 2;
    public static final int STATUS_LOAD_SUCCESS = 3;
    public ObservableField<Integer> loadStatusCode = new ObservableField<>();
    public ObservableField<String> loadStatusMsg = new ObservableField<>();

    public ObservableField<Integer> getLoadStatusCode() {
        return loadStatusCode;
    }

    public void setLoadStatusCode(ObservableField<Integer> loadStatusCode) {
        this.loadStatusCode = loadStatusCode;
    }

    public ObservableField<String> getLoadStatusMsg() {
        return loadStatusMsg;
    }

    public void setLoadStatusMsg(ObservableField<String> loadStatusMsg) {
        this.loadStatusMsg = loadStatusMsg;
    }
}
