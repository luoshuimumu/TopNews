package com.example.luoshuimumu.TopNews.net.retrofit.exception;

/**
 * Created by luoshuimumu on 2018/1/8.
 */

public interface ErrorCode {
    String RESPONSE_CODE_JSON_PARSE_ERROR = "-1";
    String RESPONSE_MSG_JSON_PARSE_ERROR = "服务器返回json格式错误";

    String RESPONSE_CODE_RESPONSE_PARSE_ERROR = "-2";
    String RESPONSE_MSG_RESPONSE_PARSE_ERROR = "服务器未返回可解析的响应";

    String RESPONSE_CODE_RESPONSE_FAIL = "1001";
    String RESPONSE_MSG_RESPONSE_FAIL = "服务器返回不成功的状态码";
}
