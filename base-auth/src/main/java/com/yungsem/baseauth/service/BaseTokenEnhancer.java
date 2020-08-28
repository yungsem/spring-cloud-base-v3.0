package com.yungsem.baseauth.service;

import com.yungsem.baseauth.enums.GrantTypeEnum;
import com.yungsem.basecommon.pojo.entity.auth.AuthUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-22
 */
public class BaseTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (GrantTypeEnum.CLIENT.getCode().equals(authentication.getOAuth2Request().getGrantType())) {
            return accessToken;
        }

        final Map<String, Object> additionalInfo = new HashMap<>(4);
        AuthUser authUser = (AuthUser) authentication.getUserAuthentication().getPrincipal();
        additionalInfo.put("id", authUser.getId());
        additionalInfo.put("code", authUser.getCode());
        additionalInfo.put("username", authUser.getUsername());
        additionalInfo.put("realName", authUser.getRealName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
