package com.yungsem.baseauth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-22
 */
@AllArgsConstructor
public enum GrantTypeEnum {
    PASSWORD("PASSWORD", "密码模式"),
    CLIENT("CLIENT", "客户端模式"),
    ;

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String message;
}
