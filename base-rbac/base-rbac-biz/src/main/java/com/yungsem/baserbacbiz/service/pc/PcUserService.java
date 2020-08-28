package com.yungsem.baserbacbiz.service.pc;

import cn.hutool.core.bean.BeanUtil;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import com.yungsem.basecommon.pojo.param.rbac.UserAddParam;
import com.yungsem.baserbacbiz.service.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户模块 PC 端业务
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Slf4j
@Service
public class PcUserService {
    @Resource
    private UserService userService;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    public void addUser(UserAddParam param) {
        UserEntity entity = new UserEntity();
        BeanUtil.copyProperties(param, entity);
        // entity.setPassword(passwordEncoder.encode("123456"));
        userService.save(entity);
    }
}
