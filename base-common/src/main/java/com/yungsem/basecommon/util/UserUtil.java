package com.yungsem.basecommon.util;

import cn.hutool.core.bean.BeanUtil;
import com.yungsem.basecommon.pojo.entity.auth.AuthUser;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
        if (principal instanceof AuthUser) {
            AuthUser authUser = (AuthUser) principal;
            UserEntity userEntity = new UserEntity();
            BeanUtil.copyProperties(authUser, userEntity);
            return userEntity;
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername("anonymous");
            userEntity.setRealName("匿名");
            return userEntity;
        }
    }
}
