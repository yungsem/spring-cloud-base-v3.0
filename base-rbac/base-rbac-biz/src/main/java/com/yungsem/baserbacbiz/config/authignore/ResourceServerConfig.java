package com.yungsem.baserbacbiz.config.authignore;

import com.yungsem.basecommon.config.auth.BaseUserAuthenticationConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import javax.annotation.Resource;


@Slf4j
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Resource
    private AuthIgnoreConfig authIgnoreConfig;
    @Resource
    protected RemoteTokenServices remoteTokenServices;

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

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        BaseUserAuthenticationConverter userTokenConverter = new BaseUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);

        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        resources.tokenServices(remoteTokenServices);
    }
}
