package com.yungsem.basebusinessbiz.service.remote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户模块 feign 端业务
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Slf4j
@Service
public class RemoteNoTokenService {

    public String testNoToken() {
        return "no token test success";
    }
}
