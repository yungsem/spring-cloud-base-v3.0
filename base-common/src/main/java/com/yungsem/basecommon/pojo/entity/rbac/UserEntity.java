package com.yungsem.basecommon.pojo.entity.rbac;

import com.yungsem.basecommon.pojo.entity.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String realName;
}
