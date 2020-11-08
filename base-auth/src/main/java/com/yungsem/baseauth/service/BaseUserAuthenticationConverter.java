package com.yungsem.baseauth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-11-08
 */
@Component
public class BaseUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        response.put("user_name", authentication.getPrincipal());
        return response;
    }
}
