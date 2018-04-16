package com.example.luoshuimumu.TopNews.net.service;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by luoshuimumu on 2017/11/8.
 */

public interface BaseService {
    @FormUrlEncoded
    @POST("{url}")
    Observable<ResponseBody> post(@Path("url") String url,
                                  @Field("params") String params
            , @Field("signature") String signature);


    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> params);
//    ,
//                                 @Query("params") String params
//            , @Query("signature") String signature);

}
