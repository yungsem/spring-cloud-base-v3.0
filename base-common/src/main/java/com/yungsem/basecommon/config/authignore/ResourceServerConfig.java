package com.yungsem.basecommon.config.authignore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.annotation.Resource;


@Slf4j
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Resource
    private AuthIgnoreConfig authIgnoreConfig;

    /**
     * 加了 @authIgnore 注解的 controller 方法，可以不用带 token 进行 feign 调用
     *
     * @param httpSecurity httpSecurity
     * @throws Exception Exception
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        String[] urls = authIgnoreConfig.getIgnoreUrls().stream().distinct().toArray(String[]::new);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
        registry.antMatchers(urls).permitAll();
        registry.anyRequest().authenticated()
                .and().csrf().disable();
    }
}
