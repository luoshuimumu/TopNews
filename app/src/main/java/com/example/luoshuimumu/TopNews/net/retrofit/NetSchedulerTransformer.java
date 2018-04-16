package com.example.luoshuimumu.TopNews.net.retrofit;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程调度转换
 * Created by luoshuimumu on 2017/11/8.
 */

public class NetSchedulerTransformer<T> implements
        ObservableTransformer<T, T> {
    @Override
    public ObservableSource<T> apply(Observable<T> observable) {
        return
                //指定被观察者的观察线程时，仅第一次指定时有效
                observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        //每次切换线程都会起作用
                        .observeOn(AndroidSchedulers.mainThread())
                ;
    }
}