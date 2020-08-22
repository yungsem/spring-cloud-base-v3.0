package com.yungsem.basecommon.pojo.entity.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private Date createTime;
    private Date updateTime;
    private String createUserCode;
    private String updateUserCode;
    private String createUserName;
    private String updateUserName;
    private Integer delFlag;
}
