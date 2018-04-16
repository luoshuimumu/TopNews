package com.example.luoshuimumu.TopNews.net.service;

import com.example.luoshuimumu.TopNews.constant.ApiConstant;
import com.example.luoshuimumu.TopNews.gankio.entity.resp.GankDayResp;
import com.example.luoshuimumu.TopNews.net.BaseApiHelper;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;


/**
 * 头条新闻访问 model
 * Created by luoshuimumu on 2017/11/9.
 */

public class TopNewsApiHelper extends BaseApiHelper {

    public Observable<ArrayList<String>> getIdList(Map<String, String> params) {
        return doGetRequest(ArrayList.class, ApiConstant.dayHistory, params)
//                //TODO 在这里如果 baseResponse 为空了会怎么样
//                //有可能转义失败？？？
//                .flatMap(news -> {
//                            Gson gson = new Gson();
//                            String responseStr = gson.toJson(baseResponse);
//                            News news = gson.fromJson(responseStr, News.class);
//                            return
//                                    Observable.just(news);
//                        }
//                )
                ;


    }

    public Observable<GankDayResp> getOneList(String year, String month, String day) {
//        return doGetRequest(GankDayResp.class, ApiConstant.dayData
//                + "/" + year + "/" + month + "/" + day, null)
//                ;
        return doGetRequest(GankDayResp.class, ApiConstant.dayData
                + "/" + year + "/" + month + "/" + day, null)
                ;
    }
}
