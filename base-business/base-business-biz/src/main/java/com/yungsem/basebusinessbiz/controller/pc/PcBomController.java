package com.yungsem.basebusinessbiz.controller.pc;

import com.yungsem.basebusinessbiz.service.pc.PcBomService;
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
@RequestMapping("/business/pc")
public class PcBomController {
    @Resource
    private PcBomService pcBomService;

    @GetMapping("/bom/doBusiness")
    public String doBusiness() {
        return pcBomService.doBusiness();
    }
}
