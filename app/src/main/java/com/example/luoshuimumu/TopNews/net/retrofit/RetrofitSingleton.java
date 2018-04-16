package com.example.luoshuimumu.TopNews.net.retrofit;

import com.example.luoshuimumu.TopNews.constant.ApiConstant;
import com.example.luoshuimumu.TopNews.net.retrofit.jsonparsor.GankJsonParsor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 提供访问网络的 retrofit 单例
 * Created by luoshuimumu on 2017/11/8.
 */

public class RetrofitSingleton {
    private static Retrofit retrofit;

    private RetrofitSingleton() {

    }

    public static Retrofit getInstance() {
        if (null == retrofit) {
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            okHttpClient.connectTimeout(10, TimeUnit.SECONDS);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new ApiGsonTypeAdapterFactory(new GankJsonParsor()))
                    .create();
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient.build())
                    .baseUrl(ApiConstant.BASE_URL)
                    //添加转换
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //在这里可以自定义返回的 gson 的解析方案，比如异常信息就抛错等等
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .build();
        }
        return retrofit;
    }

}
