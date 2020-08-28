package com.yungsem.baseauth.service;

import com.yungsem.basecommon.pojo.entity.auth.AuthUser;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import com.yungsem.baserbacapi.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangsen
 */
@Slf4j
@Service("userDetailsService")
public class BaseUserDetailsService implements UserDetailsService {
    // @Resource
    // private PasswordEncoder passwordEncoder;
    @Resource
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = remoteUserService.getByUsername(s);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return new AuthUser(userEntity.getUsername(), userEntity.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"),
                userEntity.getId(), userEntity.getCode(), userEntity.getRealName());
    }
}
