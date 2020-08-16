package com.yungsem.baseauth.service;

import com.yungsem.baserbacapi.feign.RemoteUserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangsen
 */
@Service("userDetailsService")
public class BaseUserDetailsService implements UserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // UserEntity userEntity = remoteUserService.getByUsername("admin");

        String password = passwordEncoder.encode("123456");
        List<User> userList = new ArrayList<>();
        userList.add(new User("macro", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("andy", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("mark", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));

        List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(s)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }
}
