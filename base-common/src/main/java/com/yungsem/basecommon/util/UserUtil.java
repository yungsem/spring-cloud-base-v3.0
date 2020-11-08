package com.yungsem.basecommon.util;

import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-17
 */
public class UserUtil {

    /**
     * 获取当前登录用户
     *
     * @return UserEntity
     */
    public static UserEntity getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) principal;
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername((String)map.get("username"));
            userEntity.setRealName((String)map.get("realName"));
            userEntity.setId(Long.valueOf((Integer)map.get("id")));
            userEntity.setCode((String)map.get("code"));
            return userEntity;
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setCode("anonymous");
            userEntity.setUsername("anonymous");
            userEntity.setRealName("匿名");
            return userEntity;
        }
    }
}
