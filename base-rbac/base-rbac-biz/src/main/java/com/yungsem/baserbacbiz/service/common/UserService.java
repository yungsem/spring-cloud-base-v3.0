package com.yungsem.baserbacbiz.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yungsem.basecommon.pojo.entity.rbac.User;

import java.util.List;

/**
 * 用户
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-21
 */
public interface UserService extends IService<User> {

    /**
     * 获取
     *
     * @param username 用户名
     * @return UserEntity
     */
    User getByUsername(String username);

    /**
     * 获取
     *
     * @param realName 姓名
     * @return List<UserEntity>
     */
    List<User> getByRealName(String realName);

    /**
     * 批量插入
     *
     * @param userList 用户
     */
    void insertBatch(List<User> userList);

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
