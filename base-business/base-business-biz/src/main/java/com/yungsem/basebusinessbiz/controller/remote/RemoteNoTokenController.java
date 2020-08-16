package com.yungsem.basebusinessbiz.controller.remote;

import com.yungsem.basebusinessbiz.service.remote.RemoteNoTokenService;
import com.yungsem.basecommon.config.annotation.AuthIgnore;
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
@RequestMapping("/business/remote")
public class RemoteNoTokenController {
    @Resource
    private RemoteNoTokenService remoteNoTokenService;

    @AuthIgnore
    @GetMapping("/notoken/test")
    public String testNoToken() {
        return remoteNoTokenService.testNoToken();
    }
}
