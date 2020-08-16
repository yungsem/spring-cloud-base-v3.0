package com.yungsem.baserbacapi.feign;

import com.yungsem.basecommon.config.feign.FeignConfiguration;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@FeignClient(contextId = "remoteRbacService", value = "base-rbac-biz", configuration = FeignConfiguration.class)
public interface RemoteUserService {

    @GetMapping("/rbac/remote/user/getByUsername")
    UserEntity getByUsername(@RequestParam(value = "username") String username);
}
