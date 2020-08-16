package com.yungsem.baserbacbiz.controller;

import com.yungsem.baserbacbiz.service.UserService;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangsen
 */
@RefreshScope
@RestController
@RequestMapping("/rbac")
public class UserController {
    // @Value("${server-name}")
    // private String serverName;
    @Resource
    private UserService userService;

    // @GetMapping("/user/config/serverName")
    // public String getConfig() {
    //     return serverName;
    // }

    @GetMapping("/user/getByUsername")
    public UserEntity getByUsername(@RequestParam(value = "username") String username) {
        return userService.getByUsername(username);
    }
}
