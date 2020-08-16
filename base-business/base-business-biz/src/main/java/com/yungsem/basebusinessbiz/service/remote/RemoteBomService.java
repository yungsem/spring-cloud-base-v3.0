package com.yungsem.basebusinessbiz.service.remote;

import com.yungsem.basebusinessbiz.service.common.BomService;
import com.yungsem.basecommon.pojo.entity.business.BomEntity;
import com.yungsem.baserbacapi.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户模块 feign 端业务
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Slf4j
@Service
public class RemoteBomService {
    @Resource
    private BomService bomService;


    public BomEntity getByMaterialCode(String materialCode) {
        return bomService.getByMaterialCode(materialCode);
    }
}
