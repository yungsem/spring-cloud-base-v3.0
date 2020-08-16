package com.yungsem.basebusinessbiz.controller.remote;

import com.yungsem.basebusinessbiz.service.remote.RemoteBomService;
import com.yungsem.basecommon.pojo.entity.business.BomEntity;
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
@RequestMapping("/business/remote")
public class RemoteBomController {
    @Resource
    private RemoteBomService remoteBomService;

    @GetMapping("/bom/getByMaterialCode")
    public BomEntity getByMaterialCode(@RequestParam(value = "materialCode") String materialCode) {
        return remoteBomService.getByMaterialCode(materialCode);
    }
}
