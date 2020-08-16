package com.yungsem.baseauth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Date;

/**
 * @author yangsen
 */
@Slf4j
public class BaseRedisTokenStore extends RedisTokenStore {
    /**
     * token 剩余有效时间的下限，单位秒
     */
    private final long MIN_LIMIT_REMAIN = 60;

    /**
     * 自动续签的时长，2 小时
     */
    private final long CONTINUE_HOURS_IN_MILLS = 2 * 3600 * 1000;

    public BaseRedisTokenStore(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * 重写父类的 readAuthentication 方法，增加 token 自动刷新
     */
    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        // 借用父类 readAuthentication 方法
        OAuth2Authentication authentication = super.readAuthentication(token.getValue());

        // 获取该 token 剩余有效时间
        int expiresIn = token.getExpiresIn();

        // 如果 token 剩余有效时间小于 60 秒，则自动续签 2 小时
        if (expiresIn < MIN_LIMIT_REMAIN) {
            // 续签 2 小时
            log.info("=====>>>token 剩余有效时间：{} 秒", expiresIn);
            ((DefaultOAuth2AccessToken) token).setExpiration(new Date(System.currentTimeMillis() + CONTINUE_HOURS_IN_MILLS));
            log.info("=====>>>续签后 token 剩余有效时间：{} 秒", token.getExpiresIn());

            // 重新存储 token
            super.storeAccessToken(token, authentication);
        }

        // 返回
        return authentication;
    }
}
