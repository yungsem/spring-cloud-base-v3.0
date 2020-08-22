package com.yungsem.basecommon.pojo.common;

import lombok.Data;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-21
 */
@Data
public class R<T> {
    private String code;
    private String message;
    private T data;
}
