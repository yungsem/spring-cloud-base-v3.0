package com.yungsem.basecommon.pojo.common;

import lombok.Data;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-21
 */
@Data
public class R<T> {
    private int code;
    private String message;
    private T data;

    public static final int CODE_OK = 0;
    public static final int CODE_FAIL = 1;
    public static final String MESSAGE_OK = "成功";

    public static <T> R<T> ok(T data) {
        return build(CODE_OK, MESSAGE_OK, data);
    }

    public static <T> R<T> ok() {
        return build(CODE_OK, MESSAGE_OK, null);
    }

    public static <T> R<T> failed(String message) {
        return build(CODE_FAIL, message, null);
    }

    private static <T> R<T> build(int code, String message, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}
