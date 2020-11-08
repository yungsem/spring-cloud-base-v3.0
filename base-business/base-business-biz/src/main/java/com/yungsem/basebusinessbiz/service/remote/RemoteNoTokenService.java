package com.yungsem.basebusinessbiz.service.remote;

import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import com.yungsem.basecommon.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块 feign 端业务
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Slf4j
@Service
public class RemoteNoTokenService {

    public String testNoToken() {
        UserEntity loginUser = UserUtil.getLoginUser();
        System.out.println(loginUser.toString());
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String authorization = request.getHeader("Authorization");
        return "no token test success";
    }
}
