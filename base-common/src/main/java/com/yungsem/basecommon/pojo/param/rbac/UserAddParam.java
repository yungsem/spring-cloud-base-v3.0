package com.yungsem.basecommon.pojo.param.rbac;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户新增
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-21
 */
@Data
@ApiModel(value = "用户新增", description = "用户新增")
public class UserAddParam  {
    @ApiModelProperty(value = "用户名", example = "用户名")
    private String username;
    @ApiModelProperty(value = "用户姓名", example = "用户姓名")
    private String realName;
}
