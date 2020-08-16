package com.yungsem.basecommon.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {

    /**
     * token 往下透传
     *
     * @param template template
     */
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            template.header("Authorization", request.getHeader("Authorization"));
        }
    }
}
