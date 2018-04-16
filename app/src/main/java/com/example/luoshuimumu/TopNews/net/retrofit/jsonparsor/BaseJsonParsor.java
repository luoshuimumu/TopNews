package com.example.luoshuimumu.TopNews.net.retrofit.jsonparsor;

import com.example.luoshuimumu.TopNews.net.retrofit.exception.ApiJsonParseErrorException;
import com.google.gson.JsonObject;

/**
 * Created by luoshuimumu on 2018/1/8.
 */

public interface BaseJsonParsor {
    JsonObject parseResponseStructure(JsonObject jsonObject) throws ApiJsonParseErrorException;
}
