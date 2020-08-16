package com.yungsem.basebusinessbiz.controller.app;

import com.yungsem.basebusinessbiz.service.app.AppBomService;
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
@RequestMapping("/business/app")
public class AppBomController {
    @Resource
    private AppBomService appBomService;

    @GetMapping("/bom/doBusiness")
    public String doBusiness() {
        return appBomService.doBusiness();
    }
}
