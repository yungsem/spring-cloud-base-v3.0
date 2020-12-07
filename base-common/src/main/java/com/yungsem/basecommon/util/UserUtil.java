package com.yungsem.basecommon.util;

import com.yungsem.basecommon.pojo.entity.rbac.User;
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
    public static User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) principal;
            User user = new User();
            user.setUsername((String)map.get("username"));
            user.setRealName((String)map.get("realName"));
            user.setId(Long.valueOf((Integer)map.get("id")));
            user.setCode((String)map.get("code"));
            return user;
        } else {
            User user = new User();
            user.setCode("anonymous");
            user.setUsername("anonymous");
            user.setRealName("匿名");
            return user;
        }
    }
}
