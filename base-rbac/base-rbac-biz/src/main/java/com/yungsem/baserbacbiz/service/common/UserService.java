package com.yungsem.baserbacbiz.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;

import java.util.List;

/**
 * 用户
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-21
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 获取
     *
     * @param username 用户名
     * @return UserEntity
     */
    UserEntity getByUsername(String username);

    /**
     * 获取
     *
     * @param realName 姓名
     * @return List<UserEntity>
     */
    List<UserEntity> getByRealName(String realName);

    /**
     * 批量插入
     *
     * @param userEntityList 用户
     */
    void insertBatch(List<UserEntity> userEntityList);

    /**
     * 删除，物理删除
     *
     * @param id ID 主键
     */
    void deleteById(Long id);

    /**
     * 批量删除，物理删除
     *
     * @param idList ID 主键 List
     */
    void deleteByIds(List<Long> idList);
}
