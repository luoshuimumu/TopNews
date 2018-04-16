package com.example.luoshuimumu.TopNews.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.luoshuimumu.TopNews.net.retrofit.exception.ApiJsonParseErrorException;
import com.example.luoshuimumu.TopNews.net.retrofit.exception.NoResponseDataException;
import com.example.luoshuimumu.TopNews.utils.LogUtil;
import com.trello.rxlifecycle2.components.RxFragment;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * Created by luoshuimumu on 2017/11/13.
 */

public class BaseViewModel {
    //可能需要持有的还有 fragment
    protected Context mContext;
    protected RxFragment mFragment;
    protected IView mView;

    public BaseViewModel(Context context) {
        this.mContext = context;
    }

    public BaseViewModel(RxFragment fragment) {
        this.mFragment = fragment;
        this.mContext = fragment.getActivity();
    }

    /**
     * 访问网络时 json 解析错误回调的方法
     * 展示错误页面
     */
    protected void onJsonParseError(ApiJsonParseErrorException e) {
//TODO modify
    }

    /**
     * 意料之外的错误
     */
    protected void onUnexceptedError(Throwable e) {
        //TODO modify
        Log.e(this.getClass().getSimpleName(), "" + e.getMessage());
        e.printStackTrace();
    }

    protected void onNoResponseDataError(NoResponseDataException e) {
        Log.e(this.getClass().getSimpleName(), "" + e.getMessage());
    }

    interface onErrorCallback {
        void onError(Object object);
    }

    /**
     * 处理 json 解析错误以及未知错误的容错处理
     * 获取默认的网络错误处理监听器 这是mvp的写法?
     *
     * @return
     */
    protected Consumer<Throwable> getDefaultErrorSubscriber() {
        return e -> {
            //顺序判断错误类型，分别处理
            if (e instanceof ApiJsonParseErrorException
                    ) {
                onJsonParseError((ApiJsonParseErrorException) e);
            } else if (e instanceof NoResponseDataException) {
                onNoResponseDataError((NoResponseDataException) e);
            } else {
                onUnexceptedError(e);
            }
        };
    }

    protected void handleError(Throwable e) {
        String msg = null != e ? e.getMessage() : "";
        Toast.makeText(mContext, "" + msg, Toast.LENGTH_SHORT).show();
        LogUtil.e(this.getClass().getSimpleName(), msg);
        LogUtil.e(e);
    }

//    public interface CallbackHandler {
//        void onError();
//
//        void onNext();
//    }

    public Observable requestWrap(Observable observable) {
        if (null != observable) {
            observable
                    .compose(mFragment.bindToLifecycle())
//                    .compose(new ObservableTransformer() {
//                        @Override
//                        public ObservableSource apply(Observable upstream) {
//                            return null;
//                        }
//                    })
                    .doOnEach(
                            params -> {
                                //TODO 展示loading？
                            })
                    .doOnComplete(() -> {
                        //TODO 隐藏loading
                    })
            ;
        }
        return observable;
    }

    class RequestCallback {

    }
}
