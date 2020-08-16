package com.yungsem.basebusinessbiz.service.common;

import com.yungsem.basecommon.pojo.entity.business.BomEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Service
@Slf4j
public class BomService {

    public BomEntity getByMaterialCode(String materialCode) {
        BomEntity bomEntity = new BomEntity();
        bomEntity.setMaterialCode(materialCode);
        return bomEntity;
    }
}
