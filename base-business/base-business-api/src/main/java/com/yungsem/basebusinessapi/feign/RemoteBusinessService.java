package com.yungsem.basebusinessapi.feign;

import com.yungsem.basecommon.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@FeignClient(contextId = "remoteBusinessService", value = "base-business-biz", configuration = FeignConfiguration.class)
public interface RemoteBusinessService {

    @GetMapping("/business/user/testFeign")
    String testFeign(@RequestParam(value = "biz") String biz);
}
