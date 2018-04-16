package com.example.luoshuimumu.TopNews.net.retrofit.jsonparsor;


import com.example.luoshuimumu.TopNews.net.retrofit.exception.ApiJsonParseErrorException;
import com.example.luoshuimumu.TopNews.net.retrofit.exception.ErrorCode;
import com.google.gson.JsonObject;

/**
 * Created by luoshuimumu on 2018/1/8.
 */

public class ZhihuDailyJsonParsor implements BaseJsonParsor {
    @Override
    public JsonObject parseResponseStructure(JsonObject jsonObject) throws ApiJsonParseErrorException {
        if (null == jsonObject) {
            throw new ApiJsonParseErrorException(ErrorCode.RESPONSE_CODE_JSON_PARSE_ERROR, ErrorCode.RESPONSE_CODE_JSON_PARSE_ERROR);
        }
//        JsonObject result = new JsonObject();
//        result.add("data", jsonObject);
        return jsonObject;
//        return result;
    }
}
