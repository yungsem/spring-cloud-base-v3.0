package com.yungsem.basebusinessbiz.controller;

import com.yungsem.basebusinessbiz.service.BusinessService;
import com.yungsem.basecommon.pojo.entity.business.BusinessEntity;
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
@RequestMapping("/business")
public class BusinessController {
    // @Value("${server-name}")
    // private String serverName;
    @Resource
    private BusinessService businessService;

    // @GetMapping("/user/config/serverName")
    // public String getConfig() {
    //     return serverName;
    // }

    @GetMapping("/user/getByBiz")
    public BusinessEntity getByBiz(@RequestParam(value = "biz") String biz) {
        return businessService.getByBiz(biz);
    }

    @GetMapping("/user/testFeign")
    public String testFeign(@RequestParam(value = "biz") String biz) {
        return biz;
    }

}
