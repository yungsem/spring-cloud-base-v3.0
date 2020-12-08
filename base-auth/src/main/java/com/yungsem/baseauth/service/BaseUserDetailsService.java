package com.yungsem.baseauth.service;

import com.yungsem.basecommon.pojo.entity.auth.AuthUser;
import com.yungsem.basecommon.pojo.entity.rbac.User;
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
@Service
public class BaseUserDetailsService implements UserDetailsService {
    @Resource
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = remoteUserService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 构建 authUser
        return this.buildAuthUser(user);
    }

    /**
     * 构建 authUser
     *
     * @param user user
     * @return AuthUser
     */
    private AuthUser buildAuthUser(User user) {
        // 走到此处时就已经经过了 Client 认证，Client 认证会将 client_id 和 client_secret 作为 username 和 password
        // 封装到 authentication 中
        // org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User)
        //                 SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // String clientId = u.getUsername();


        AuthUser authUser = new AuthUser(user.getUsername(), user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        authUser.setId(user.getId());
        authUser.setCode(user.getCode());
        authUser.setRealName(user.getRealName());
        return authUser;
    }
}
