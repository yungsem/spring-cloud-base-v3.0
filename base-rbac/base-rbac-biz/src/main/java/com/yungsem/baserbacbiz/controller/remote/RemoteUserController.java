package com.yungsem.baserbacbiz.controller.remote;

import com.yungsem.basecommon.config.annotation.AuthIgnore;
import com.yungsem.basecommon.pojo.entity.rbac.User;
import com.yungsem.baserbacbiz.service.remote.RemoteUserService;
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
@RequestMapping("/rbac/remote")
public class RemoteUserController {
    @Resource
    private RemoteUserService remoteUserService;

    @AuthIgnore
    @GetMapping("/user/getByUsername")
    public User getUserByUsername(@RequestParam(value = "username") String username) {
        return remoteUserService.getUserByUsername(username);
    }
}
