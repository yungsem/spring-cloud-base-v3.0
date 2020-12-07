package com.yungsem.basebusinessbiz.service.pc;

import com.yungsem.basecommon.pojo.entity.rbac.User;
import com.yungsem.baserbacapi.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * BOM 模块 PC 端业务
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Slf4j
@Service
public class PcBomService {
    @Resource
    private RemoteUserService remoteUserService;

    public String doBusiness() {
        User user = remoteUserService.getByUsername("admin");
        log.info("=====>>>{}", user);
        return "BOM 模块 PC 端业务";
    }
}
