package com.yungsem.basecommon.pojo.entity.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-22
 */

public class AuthUser extends User {
    @Getter@Setter
    private Long id;
    @Getter@Setter
    private String code;
    @Getter@Setter
    private String realName;


    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
