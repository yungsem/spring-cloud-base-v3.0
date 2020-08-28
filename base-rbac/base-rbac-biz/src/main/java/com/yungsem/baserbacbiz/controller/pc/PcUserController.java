package com.yungsem.baserbacbiz.controller.pc;

import com.yungsem.basecommon.pojo.common.R;
import com.yungsem.basecommon.pojo.param.rbac.UserAddParam;
import com.yungsem.baserbacbiz.service.pc.PcUserService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/add")
    public R<String> addUser(@RequestBody UserAddParam param) {
        pcUserService.addUser(param);
        return R.ok();
    }
}
