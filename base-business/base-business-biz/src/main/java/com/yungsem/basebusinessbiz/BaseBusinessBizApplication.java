package com.yungsem.basebusinessbiz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@ComponentScan(basePackages = {"com.yungsem.basebusinessbiz", "com.yungsem.basecommon.config"})
@MapperScan("com.yungsem.basebusinessbiz.mapper")
@EnableFeignClients(basePackages = {"com.yungsem.baserbacapi.feign"}) // 添加对 feign api 包的扫描
@EnableResourceServer // 开启资源保护
@EnableCaching
@SpringBootApplication
@EnableDiscoveryClient
public class BaseBusinessBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseBusinessBizApplication.class, args);
    }

}
