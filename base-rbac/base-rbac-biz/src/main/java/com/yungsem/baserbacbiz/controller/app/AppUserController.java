package com.yungsem.baserbacbiz.controller.app;

import com.yungsem.baserbacbiz.service.app.AppUserService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangsen
 */
@RefreshScope
@RestController
@RequestMapping("/rbac/app")
public class AppUserController {
    @Resource
    private AppUserService appUserService;

    @GetMapping("/user/doBusiness")
    public String doBusiness() {
        return appUserService.doBusiness();
    }
}
