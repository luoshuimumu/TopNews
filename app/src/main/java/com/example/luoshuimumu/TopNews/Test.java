package com.example.luoshuimumu.TopNews;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by luoshuimumu on 2018/2/23.
 */

public class Test {

    private void getUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
//        matcher.addURI();
    }

    ContentProvider contentProvider = new ContentProvider() {
        @Override
        public boolean onCreate() {
            return false;
        }

        @Nullable
        @Override
        public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
            return null;
        }

        @Nullable
        @Override
        public String getType(@NonNull Uri uri) {
            return null;
        }

        @Nullable
        @Override
        public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
            return null;
        }

        @Override
        public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
            return 0;
        }

        @Override
        public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String s, @Nullable String[] strings) {
            return 0;
        }
    };


    public void test() {
        //被观察者
        Observable<Integer> observable = io.reactivex.Observable.create((emmitter) -> {
            emmitter.onNext(1);
            emmitter.onComplete();
        });
        observable.subscribeOn(io.reactivex.schedulers.Schedulers.io());
        observable.subscribe(
                //观察者
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
//                        d.dispose();//切断连接
                    }

                    @Override
                    public void onNext(Integer value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //rx2.0版本的观察者模型
        //2.0版本中的被观察者 支持背压 TODO what背压？？
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
                                                         @Override
                                                         public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                                                             //发送三个事件
                                                             emitter.onNext(1);
                                                             emitter.onNext(2);
                                                             emitter.onNext(3);
                                                             emitter.onComplete();
                                                         }
                                                     },
                //当缓存区满时，被观察者继续发送事件的应对策略 error是直接抛异常
                BackpressureStrategy.ERROR);
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                //TODO 可以讲 subscription 设置到外部，在点击事件等位置领其接收事件
                //比如没点击一次接收两个事件
//                s.request(2);
                //未设置则观察者不接受事件，但事件会被发送到缓冲区，超过128个时溢出报错
//                s.request(1);//设置观察者可以接收的事件数量，多出的放在缓冲区
//                s.request(Long.MAX_VALUE);//官方推荐
//                s.cancel();

                //TODO 同步情况下若观察者无法接收事件，会直接抛出异常
            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        flowable.subscribe(subscriber);
    }


    class MyRunable implements Runnable {
        @Override
        public void run() {
            //do sometting
//            Thread.currentThread().xxxx;
        }
    }

    {
        Thread myThread = new Thread(new MyRunable());
        myThread.start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }

    {
        new MyThread().start();

    }

    class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return null;
        }
    }

    {
        //让线程运行
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        Thread thread = new Thread(task);
        thread.start();
        //获取结果
        try {
            String result = task.get();
        } catch (ExecutionException | InterruptedException e) {

        }
    }
}
