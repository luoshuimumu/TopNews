package com.example.luoshuimumu.TopNews.net.retrofit.exception;

import java.io.IOException;

/**
 * 需要在 解析gson 层抛出所以继承 io异常
 * Created by luoshuimumu on 2017/11/13.
 */

public class ApiJsonParseErrorException extends IOException {
    public ApiJsonParseErrorException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    String code;
    String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
