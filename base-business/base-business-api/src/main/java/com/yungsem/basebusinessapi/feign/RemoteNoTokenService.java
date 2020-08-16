package com.yungsem.basebusinessapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@FeignClient(contextId = "remoteNoTokenService", value = "base-business-biz")
public interface RemoteNoTokenService {

    @GetMapping("/business/remote/notoken/test")
    public String testNoToken();
}
