package com.yungsem.baserbacbiz.service.pc;

import com.yungsem.basebusinessapi.feign.RemoteBomService;
import com.yungsem.basebusinessapi.feign.RemoteNoTokenService;
import com.yungsem.basecommon.pojo.entity.business.BomEntity;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import com.yungsem.baserbacbiz.service.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户模块 PC 端业务
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Slf4j
@Service
public class PcUserService {
    @Resource
    private RemoteBomService remoteBomService;
    @Resource
    private RemoteNoTokenService remoteNoTokenService;
    @Resource
    private UserService userService;

    public String doBusiness() {
        BomEntity bomEntity = remoteBomService.getByMaterialCode("010001");
        log.info("=====>>>{}", bomEntity);
        return "用户模块 PC 端业务";
    }

    public String testNoToken() {
        return remoteNoTokenService.testNoToken();
    }

    public UserEntity getByUsername(String username) {
        return userService.getByUsername(username);
    }
}
