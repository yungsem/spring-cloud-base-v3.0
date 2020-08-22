package com.yungsem.baserbacbiz.controller.pc;

import com.yungsem.basecommon.config.annotation.AuthIgnore;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import com.yungsem.baserbacbiz.service.pc.PcUserService;
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
@RequestMapping("/rbac/pc")
public class PcUserController {
    @Resource
    private PcUserService pcUserService;

    @GetMapping("/user/doBusiness")
    public String doBusiness() {
        return pcUserService.doBusiness();
    }

    @AuthIgnore
    @GetMapping("/user/testNoToken")
    public String testNoToken() {
        return pcUserService.testNoToken();
    }

    @GetMapping("/user/getByUsername")
    public UserEntity getByUsername(@RequestParam(value = "username") String username) {
        return pcUserService.getByUsername(username);
    }
}
