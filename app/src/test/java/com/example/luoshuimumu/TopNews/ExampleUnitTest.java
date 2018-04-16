package com.example.luoshuimumu.TopNews;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Observable.create(subscriber -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1200 * i);
                } catch (Exception e) {

                }
                subscriber.onNext(i);
            }

        })
                .timeout(3, TimeUnit.SECONDS
                        , Observable.just(7, 8))
                .timeInterval()


                .subscribe(s -> {
                    System.out.println(System.currentTimeMillis());
                    System.out.println("vallue:" + s.getValue()
                            + ",interval:" + s.getIntervalInMilliseconds());
                });
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUsing() throws Exception {
        Observable observable = usingObserver();
        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                log("onError");
            }

            @Override
            public void onNext(Object o) {
                log("onNext" + o);
            }
        });

    }

    private Observable<Long> usingObserver() {
        return Observable.using(() -> new Animal()
                , i -> Observable
                        .timer(500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.immediate())
                , o -> o.relase());
    }

    private class Animal {
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                log("animal eat");
            }
        };

        public Animal() {
            log("create animal");
            Observable
                    .interval(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.immediate())
                    .subscribe(subscriber);
        }

        public void relase() {
            log("animal released");
            subscriber.unsubscribe();
        }

    }

    private void log(String log) {
        System.out.println(log);
    }
}