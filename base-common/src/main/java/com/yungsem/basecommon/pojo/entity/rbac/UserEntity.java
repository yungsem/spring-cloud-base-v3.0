package com.yungsem.basecommon.pojo.entity.rbac;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yungsem.basecommon.pojo.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("rbac_user")
@ApiModel(value = "用户", description = "用户")
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户名", example = "用户名")
    private String username;
    @ApiModelProperty(value = "密码", example = "密码")
    private String password;
    @ApiModelProperty(value = "用户姓名", example = "用户姓名")
    private String realName;
    @ApiModelProperty(value = "是否删除", example = "是否删除")
    private Integer isDel;
}
