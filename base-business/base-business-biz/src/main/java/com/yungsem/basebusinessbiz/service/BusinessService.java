package com.yungsem.basebusinessbiz.service;

import com.yungsem.basecommon.pojo.entity.business.BusinessEntity;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import com.yungsem.baserbacapi.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Service
@Slf4j
public class BusinessService {
    @Resource
    private RemoteUserService remoteUserService;

    public BusinessEntity getByBiz(String biz) {
        UserEntity userEntity = remoteUserService.getByUsername("admin");
        log.info("=====>>>{}", userEntity);
        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setBiz("biz");
        return businessEntity;
    }
}
