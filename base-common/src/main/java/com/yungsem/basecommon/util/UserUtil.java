package com.yungsem.basecommon.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-17
 */
public class UserUtil {
    public static User getLoginUse() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Object principal = authentication.getPrincipal();
        // if (principal instanceof RsUser) {
        //     return (RsUser) principal;
        // }
        return null;
    }
}
