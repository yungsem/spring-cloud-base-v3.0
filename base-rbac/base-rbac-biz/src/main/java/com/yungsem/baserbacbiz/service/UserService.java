package com.yungsem.baserbacbiz.service;

import com.yungsem.basebusinessapi.feign.RemoteBusinessService;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Service
@Slf4j
public class UserService {
    @Resource
    private RemoteBusinessService remoteBusinessService;

    public UserEntity getByUsername(String username) {
        String hello = remoteBusinessService.testFeign("hello");
        log.info("=====>>>{}", hello);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setRealName("管理员");
        return userEntity;
    }
}
