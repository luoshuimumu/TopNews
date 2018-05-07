package com.example.luoshuimumu.TopNews.base;

import android.databinding.ObservableField;

import com.example.luoshuimumu.TopNews.gank.vm.LoadingViewContainer;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * 不是每个viewModel都有网络操作，单独拆分出来
 * negative 每个接口对应的不同的loading变量
 * Created by luoshuimumu on 2018/5/7.
 */

public class BaseNetViewModel extends BaseViewModel {
    public BaseNetViewModel(LifecycleProvider lifecycleProvider) {
        super(lifecycleProvider);
    }

//    negative 每个接口对应的不同的loading变量 单独封装一个是没有意义的
//    public ObservableField<LoadingViewContainer>

    protected void setNetStatusLoading(ObservableField<LoadingViewContainer> status) {
        if (null != status && null != status.get()) {
            status.get().loadStatusCode.set(LoadingViewContainer.STATUS_LOADING);
        }
    }

    protected void setNetStatusSuccess(ObservableField<LoadingViewContainer> status) {
        if (null != status && null != status.get()) {
            status.get().loadStatusCode.set(LoadingViewContainer.STATUS_LOAD_SUCCESS);
        }
    }

    protected void setNetStatusFail(ObservableField<LoadingViewContainer> status) {
        if (null != status && null != status.get()) {
            status.get().loadStatusCode.set(LoadingViewContainer.STATUS_LOAD_FAIL);
        }
    }
}
