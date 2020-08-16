package com.yungsem.baserbacbiz.service.common;

import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Service
@Slf4j
public class UserService {

    public UserEntity getByUsername(String username) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setRealName("管理员");
        return userEntity;
    }
}
