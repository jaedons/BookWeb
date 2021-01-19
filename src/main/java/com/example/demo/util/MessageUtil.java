package com.example.demo.util;

import com.example.demo.domain.Result;

/**
 * 异常信息工具类
 * @author Administrator
 *
 */
public class MessageUtil {

    /** 一般错误码 */
    public static int ERROR_CODE1 = 1;
    /** 异常用的错误码 */
    public static int EROOR_CODE2 = 2;
    public static int SUCCESS_CODE = 0;
    /**
     * 成功方法
     * @param object
     * @return
     */
    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<T>();
        result.setCode(MessageUtil.SUCCESS_CODE);
        result.setMessage("SUCCESS");
        result.setData(t);
        return result;
    }

    /**
     * 成功但是
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 失败方法
     * @param <T>
     * @param code
     * @param message
     * @return
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
