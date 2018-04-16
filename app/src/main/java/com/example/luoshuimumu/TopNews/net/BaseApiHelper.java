package com.example.luoshuimumu.TopNews.net;

/*封装 retrofit 的转义、参数加解密等，其他访问网络的helper类都要继承它
 * Created by luoshuimumu on 2017/11/8.
 */

import com.example.luoshuimumu.TopNews.Constant;
import com.example.luoshuimumu.TopNews.gankio.entity.GankContent;
import com.example.luoshuimumu.TopNews.gankio.entity.resp.GankDayResp;
import com.example.luoshuimumu.TopNews.net.retrofit.NetSchedulerTransformer;
import com.example.luoshuimumu.TopNews.net.retrofit.RetrofitSingleton;
import com.example.luoshuimumu.TopNews.net.retrofit.exception.ApiFailException;
import com.example.luoshuimumu.TopNews.net.retrofit.exception.ApiJsonParseErrorException;
import com.example.luoshuimumu.TopNews.net.retrofit.exception.ErrorCode;
import com.example.luoshuimumu.TopNews.net.retrofit.exception.NoResponseDataException;
import com.example.luoshuimumu.TopNews.net.service.BaseService;
import com.example.luoshuimumu.TopNews.gank.entity.response.BaseResponse;
import com.example.luoshuimumu.TopNews.utils.LogUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 网路访问的参数封装
 */
public class BaseApiHelper {
    //访问网络肯定在model里边
    //model里边

    private BaseService service;

    public BaseApiHelper() {
        service = RetrofitSingleton.getInstance()
                .create(BaseService.class);
    }


//    protected Observable<BaseResponse> doPostRequest(String url, Map<String, String> params) {
//        String paramsStr = getParamsString(params);
//
//        return service.post(url, paramsStr, getSignature(paramsStr))
//                //在这里添加 json 的转义
//                .compose(new NetSchedulerTransformer())
//                //  自动断开连接声明周期 放在 vm 层
////                .compose(activity.bindToLifecycle())
//                //TODO gson 解析层封装了对返回参数的解析，这里需要获取
////                .map
//                ;
//    }

    protected <T> Observable doGetRequest(Class<T> clazz,
                                          String url, Map<String, String> params) {
        return service.get(url, getParamsString(params))
                //  自动断开连接声明周期 放在 vm 层
//                .compose(activity.bindToLifecycle())
                //TODO gson 解析层封装了对返回参数的解析，这里需要获取

                //返回空数据
//                .filter(baseResponse -> baseResponse != null)
                .flatMap((ResponseBody responseBody) ->
                {
                    if (null == responseBody) {
                        return Observable.error(new NoResponseDataException());
                    }
                    try {
                        Gson gson = new Gson();
                        String fooStr = responseBody.string();
                        //TODO!! 将fooStr字符串解析成 BaseResponse<clazz>类型
                        BaseResponse<T> baseResponse = gson.fromJson(fooStr, BaseResponse.class);
                        boolean error = baseResponse.isError();
                        if (error) {
                            throw new ApiFailException(ErrorCode.RESPONSE_CODE_RESPONSE_FAIL, ErrorCode.RESPONSE_MSG_RESPONSE_FAIL);
                        }
                        //转义成数据类型 重新用 fooStr 转义
                        try {

                            JSONObject jsonObject = new JSONObject(fooStr);
                            //TODO 由于gank/api/day接口的特殊性
                            /**
                             * @see GankDayResp
                             *其返回的json的key值是可变的
                             *所以这里可以单独封装，将原本的多个 GankDayResp 类型解析成
                             * 一个 GankDayResp 序列类型
                             */
                            if (clazz == GankDayResp.class) {
                                try {
                                    GankDayResp resp = new GankDayResp();
                                    JSONObject jsonArray = jsonObject.optJSONObject("results");
                                    Iterator<String> iterator = jsonArray.keys();
                                    while (iterator.hasNext()) {
                                        JSONArray jsonArray1 = (JSONArray) jsonArray.get(iterator.next());
                                        for (int i = 0; i < jsonArray1.length(); i++) {
                                            JSONObject gankContentJson = (JSONObject) jsonArray1.get(i);
                                            GankContent res = gson.fromJson(gankContentJson.toString(), GankContent.class);
                                            resp.getData().add(res);
                                        }
                                    }
                                    return Observable.just(resp);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return Observable.error(new ApiJsonParseErrorException(ErrorCode.RESPONSE_CODE_RESPONSE_PARSE_ERROR,
                                            ErrorCode.RESPONSE_MSG_RESPONSE_PARSE_ERROR));
                                }
                            }
                            T data = gson.fromJson(jsonObject.optString("results"), clazz);
                            return Observable.just(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        throw new ApiFailException(ErrorCode.RESPONSE_CODE_RESPONSE_FAIL, ErrorCode.RESPONSE_MSG_RESPONSE_FAIL);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Observable.error(new ApiJsonParseErrorException(ErrorCode.RESPONSE_CODE_RESPONSE_PARSE_ERROR,
                                ErrorCode.RESPONSE_MSG_RESPONSE_PARSE_ERROR));
                    }
                })
                .doOnError(throwable -> {
                    //这里异常的记录、上报
                    //TODO 保存本地日志
                    //TODO 上报
                    if (throwable instanceof ApiJsonParseErrorException
                            ) {
                        LogUtil.d(((ApiJsonParseErrorException) throwable).getCode()
                                , ((ApiJsonParseErrorException) throwable).getMsg());
                    } else if (throwable instanceof NoResponseDataException) {
                        LogUtil.d(throwable.getMessage());
                    } else {
                        LogUtil.e(throwable.getMessage());
                    }
                })
                //线程转换
                .compose(new NetSchedulerTransformer())
                //TODO 重试机制
                //TODO combineLatest联合判断
//                .retryWhen()
//                .retry()
                ;
    }


    /**
     * 为参数签名
     *
     * @param paramsStr
     * @return
     */
    protected String getSignature(String paramsStr) {
        return "";
    }

    /**
     * 将参数列表拼接成字符串
     *
     * @param params
     * @return
     */
    protected Map<String, String> getParamsString(Map<String, String> params) {
        if (null == params) {
            params = new HashMap<>();
        }
        params.put("channel", Constant.CHANNEL);
        params.put("version", Constant.VERSION);
        params.put("uuid", Constant.UUID);
        params.put("platform", Constant.PLATFORM);
        return params;
    }
}
