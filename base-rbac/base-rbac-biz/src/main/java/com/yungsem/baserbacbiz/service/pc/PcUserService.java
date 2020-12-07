package com.yungsem.baserbacbiz.service.pc;

import cn.hutool.core.bean.BeanUtil;
import com.yungsem.basebusinessapi.feign.RemoteBomService;
import com.yungsem.basebusinessapi.feign.RemoteNoTokenService;
import com.yungsem.basecommon.pojo.entity.business.BomEntity;
import com.yungsem.basecommon.pojo.entity.rbac.User;
import com.yungsem.basecommon.pojo.param.rbac.UserAddParam;
import com.yungsem.basecommon.util.UserUtil;
import com.yungsem.baserbacbiz.service.common.UserService;
import lombok.extern.slf4j.Slf4j;
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
    private RemoteBomService remoteBomService;
    @Resource
    private RemoteNoTokenService remoteNoTokenService;
    // @Resource
    // private BCryptPasswordEncoder passwordEncoder;

    public void addUser(UserAddParam param) {
        User loginUser = UserUtil.getLoginUser();

        BomEntity byMaterialCode = remoteBomService.getByMaterialCode("111");
        String s = remoteNoTokenService.testNoToken();
        User entity = new User();
        BeanUtil.copyProperties(param, entity);
        // entity.setPassword(passwordEncoder.encode("123456"));
        userService.save(entity);
    }
}
