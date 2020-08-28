package com.yungsem.basecommon.pojo.entity.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-22
 */
public class AuthUser extends User {
    @Getter
    private Long id;
    @Getter
    private String code;
    @Getter
    private String realName;

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired,
                    boolean credentialsNonExpired, boolean accountNonLocked,
                    Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, String code, String realName) {
        super(username, password, authorities);
        this.id = id;
        this.code = code;
        this.realName = realName;
    }
}
