package com.yungsem.baseauth.config;

import com.yungsem.baseauth.service.BaseClientDetailsService;
import com.yungsem.baseauth.service.endpointconfig.BaseRedisTokenStore;
import com.yungsem.baseauth.service.endpointconfig.BaseTokenEnhancer;
import com.yungsem.baseauth.service.endpointconfig.BaseUserAuthenticationConverter;
import com.yungsem.baseauth.service.endpointconfig.exceptiontranslate.BaseWebResponseExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

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
    private DataSource dataSource;
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService baseUserDetailsService;


    /**
     * 在进行 Client 认证时，需要将用户传过来的 client_id 和 client_secret 同已经定义好的 client_id 和 client_secret
     * 进行比较，此处配置如何获取已经定义好的 client 信息
     *
     * @param clientsConfigurer clientsConfigurer
     * @throws Exception Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clientsConfigurer) throws Exception {
        // 配置从数据库获取已经定义好的 client 信息
        BaseClientDetailsService baseClientDetailsService = new BaseClientDetailsService(dataSource);
        clientsConfigurer.withClientDetails(baseClientDetailsService);
    }

    /**
     * 认证的端点是 /oauth/token ，此处配置改端点中围绕 token 处理的一些配置
     *
     * @param endpointsConfigurer endpointsConfigurer
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) {
        // Client 认证之后要进行 HTTP Basic 认证，HTTP Basic 认证需要将用户传过来的用户名和密码同已经定义好的用户名和密码
        // 进行比较，此处配置如果获取已经定义好的用户信息
        endpointsConfigurer.userDetailsService(baseUserDetailsService);
        // 配置认证管理器 AuthenticationManager ，AuthenticationManager 的实现类是 ProviderManager ，ProviderManager 里
        // 维护了一个 List<AuthenticationProvider> ，用于做具体的认证
        endpointsConfigurer.authenticationManager(authenticationManager);
        // 配置 token 的存储方式
        endpointsConfigurer.tokenStore(tokenStore());
        // 增强 token ，往 token 里增加额外的必要信息
        endpointsConfigurer.tokenEnhancer(tokenEnhancer());
        // 转换 token
        endpointsConfigurer.accessTokenConverter(tokenConverter());
        // 异常转换器
        endpointsConfigurer.exceptionTranslator(new BaseWebResponseExceptionTranslator());
    }

    /**
     * 1. 配置要不要启用 ClientCredentialsTokenEndpointFilter
     * 2. 配置验证 token 时的访问权限
     *
     * @param security security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 请求 /oauth/token 获取 token 时，如果不设置 allowFormAuthenticationForClients ，
        // 则只走 HTTP Basic 认证，过滤器是 BasicAuthenticationFilter
        // 如果设置 allowFormAuthenticationForClients ，且请求 url 中带有参数 client_id 和 client_secret ，
        // 则先走 Client 认证，过滤器是 ClientCredentialsTokenEndpointFilter ，Client 认证通过之后，再走 HTTP Basic 认证
        // OAuth2 认证基本上都需要 Client 认证，所以此行代码需加上
        security.allowFormAuthenticationForClients();

        // Spring Security OAuth2 提供了两个端点用于验证 token （注意是验证 token ，不是获取 token ），
        // 这两个端点分别是：/oauth/check_token 和 /oauth/token_key
        // 默认情况下，这两个端点是不暴露的，默认的访问权限是 denyAll()
        // 在使用 OAuth2 的场景下，我们通常需要在资源服务器上调用 /oauth/check_token 端点来验证 token ，验证时是提供 token 的，
        // 因为已经获取到了 token ，所以要把这个端点的访问权限改为 isAuthenticated() ，表示授权后可以访问
        // 对应的配置就是如下
        security.checkTokenAccess("isAuthenticated()");
    }

    /**
     * token 存储方式
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        // 使用自定义的 RedisTokenStore ，加入了自动续签机制
        BaseRedisTokenStore baseRedisTokenStore = new BaseRedisTokenStore(redisConnectionFactory);
        baseRedisTokenStore.setPrefix("base_token:");
        return baseRedisTokenStore;
    }

    /**
     * 增强 token
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new BaseTokenEnhancer();
    }

    /**
     * token 转换
     *
     * @return DefaultAccessTokenConverter
     */
    @Bean
    public DefaultAccessTokenConverter tokenConverter() {
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        BaseUserAuthenticationConverter userAuthenticationConverter = new BaseUserAuthenticationConverter();
        tokenConverter.setUserTokenConverter(userAuthenticationConverter);
        return tokenConverter;
    }
}
