package com.yungsem.basecommon.config.authignore;

import cn.hutool.core.util.ReUtil;
import com.yungsem.basecommon.config.annotation.AuthIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Configuration
@ConfigurationProperties(prefix = "security.oauth2.client")
public class AuthIgnoreConfig implements InitializingBean {
    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)}");
    private static final String ASTERISK = "*";
    @Resource
    private WebApplicationContext applicationContext;
    @Getter
    @Setter
    private List<String> ignoreUrls = new ArrayList<>();

    /**
     * 获取 security.oauth2.client.ignore-urls 配置项指定的不需要 token 的 URI
     * 获取加了 @authIgnore 注解的 controller 方法对应的请求 URI
     * 将两者加在一起
     */
    @Override
    public void afterPropertiesSet() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(mappingInfo -> {
            HandlerMethod handlerMethod = map.get(mappingInfo);
            AuthIgnore method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), AuthIgnore.class);
            Optional.ofNullable(method)
                    .ifPresent(authIgnore -> mappingInfo
                            .getPatternsCondition()
                            .getPatterns()
                            .forEach(url -> ignoreUrls.add(ReUtil.replaceAll(url, PATTERN, ASTERISK))));
            AuthIgnore controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), AuthIgnore.class);
            Optional.ofNullable(controller)
                    .ifPresent(authIgnore -> mappingInfo
                            .getPatternsCondition()
                            .getPatterns()
                            .forEach(url -> ignoreUrls.add(ReUtil.replaceAll(url, PATTERN, ASTERISK))));
        });
    }
}
