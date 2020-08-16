package com.yungsem.basebusinessapi.feign;

import com.yungsem.basecommon.config.feign.FeignConfiguration;
import com.yungsem.basecommon.pojo.entity.business.BomEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@FeignClient(contextId = "remoteBomService", value = "base-business-biz", configuration = FeignConfiguration.class)
public interface RemoteBomService {

    @GetMapping("/business/remote/bom/getByMaterialCode")
    BomEntity getByMaterialCode(@RequestParam(value = "materialCode") String materialCode);
}
