package com.yungsem.baseauth.config;

import com.yungsem.baseauth.service.BaseClientDetailsService;
import com.yungsem.baseauth.service.BaseRedisTokenStore;
import com.yungsem.baseauth.service.BaseTokenEnhancer;
import com.yungsem.baseauth.service.BaseUserAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 认证服务器配置
 *
 * @author yangsen
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private DataSource dataSource;



    /**
     * 配置 clients
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        BaseClientDetailsService clientDetailsService = new BaseClientDetailsService(dataSource);
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 配置 endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        DefaultAccessTokenConverter converter = new DefaultAccessTokenConverter();
        BaseUserAuthenticationConverter userAuthenticationConverter
                = new BaseUserAuthenticationConverter();
        converter.setUserTokenConverter(userAuthenticationConverter);
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore()) // 使用 redis 存储 token
                .accessTokenConverter(converter)
                .userDetailsService(userDetailsService) // 配置获取用户信息的 userDetailsService ，密码模式使用
                .tokenEnhancer(tokenEnhancer()) // 增强 token 内容
                .reuseRefreshTokens(true) // 刷新 token 时，是否保持 refresh_token 不变
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
        ;
    }

    @Bean
    public TokenStore tokenStore() {
        // 使用自定义的 RedisTokenStore ，加入了自动续签机制
        BaseRedisTokenStore tokenStore = new BaseRedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix("base_token:");
        return tokenStore;
    }

    @Bean
    @Primary
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenEnhancer(tokenEnhancer());
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new BaseTokenEnhancer();
    }
}
