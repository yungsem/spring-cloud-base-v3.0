package com.yungsem.baserbacbiz.service.remote;

import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import com.yungsem.baserbacbiz.service.common.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户模块 feign 端业务
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Service
public class RemoteUserService {
    @Resource
    private UserService userService;

    public UserEntity getUserByUsername(String username) {
        return userService.getByUsername(username);
    }
}
