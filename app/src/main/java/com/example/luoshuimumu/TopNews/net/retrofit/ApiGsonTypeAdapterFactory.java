package com.example.luoshuimumu.TopNews.net.retrofit;

import com.example.luoshuimumu.TopNews.net.retrofit.exception.ApiJsonParseErrorException;
import com.example.luoshuimumu.TopNews.net.retrofit.exception.ErrorCode;
import com.example.luoshuimumu.TopNews.net.retrofit.jsonparsor.BaseJsonParsor;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * TODO 改成适配器模式 在解析底层json格式时传入不同的适配器以适应需要?
 * 如果直接重写 TypeAdapter，需要自己重写 write 函数，比较麻烦
 * 如果使用 factory 可以使用代理 typeAdapter，就是 gson 默认的 adapter
 * Created by luoshuimumu on 2017/11/13.
 */

public class ApiGsonTypeAdapterFactory implements TypeAdapterFactory {
//    private static final String RESPONSE_CODE_SUCCESS = "1000";
    BaseJsonParsor jsonParsor;

    public ApiGsonTypeAdapterFactory(BaseJsonParsor jsonParsor) {
        if (null == jsonParsor) {
            throw new RuntimeException(ApiGsonTypeAdapterFactory.class.getSimpleName() + " jsonParsor can not be null!");
        }
        this.jsonParsor = jsonParsor;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        //用默认的 adapter 写所有对象
        TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementTypeAdapter = gson.getAdapter(JsonElement.class);


        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = elementTypeAdapter.read(in);
                if (!jsonElement.isJsonObject()) {
                    //抛出解析类型错误，交给上层处理
                    //这里会由gson处理，还是提交到 rx 处理?
                    throw new ApiJsonParseErrorException(ErrorCode.RESPONSE_CODE_JSON_PARSE_ERROR, ErrorCode.RESPONSE_MSG_JSON_PARSE_ERROR);
                }
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                try {
                    jsonObject = jsonParsor.parseResponseStructure(jsonObject);
                } catch (ApiJsonParseErrorException e) {
                    e.printStackTrace();
                }

//                if (!jsonObject.has("code") && !jsonObject.has("msg")) {
//                    throw new ApiJsonParseErrorException(ErrorCode.RESPONSE_CODE_JSON_PARSE_ERROR, ErrorCode.RESPONSE_MSG_JSON_PARSE_ERROR);
//                }
//                String code = jsonObject.get("code").getAsString();
//                String msg = jsonObject.get("msg").getAsString();
//                //解析几种类型的服务器返回码
//                //TODO 如果没有 data 实体，应该同样属于成功的情况
////                if (!code.equals(RESPONSE_CODE_SUCCESS)) {
////                    //数据请求错误
////                    throw new ApiFailException(code, msg);
////                }
//                //返回code成功则解析data
//                if (jsonObject.has("data")) {
//                    jsonElement = jsonObject.get("data");
//                }
                //如果不含data数据，会返回 code + msg
                return delegate.fromJsonTree(jsonObject);
            }
        }.nullSafe();
    }

}
