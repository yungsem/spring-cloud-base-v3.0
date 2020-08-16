package com.yungsem.baseauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.yungsem.baserbacapi.feign"}) // 添加对 feign api 包的扫描
@EnableCaching
@SpringBootApplication
@EnableDiscoveryClient
public class BaseAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseAuthApplication.class, args);
    }

}
