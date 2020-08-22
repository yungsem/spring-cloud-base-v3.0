package com.yungsem.baserbacbiz.service.common.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yungsem.basecommon.pojo.entity.rbac.UserEntity;
import com.yungsem.baserbacbiz.mapper.common.UserMapper;
import com.yungsem.baserbacbiz.service.common.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    /**
     * 获取
     *
     * @param username 用户名
     * @return UserEntity
     */
    @Override
    public UserEntity getByUsername(String username) {
        LambdaQueryWrapper<UserEntity> qw = Wrappers.<UserEntity>lambdaQuery()
        .eq(UserEntity::getUsername, username);
        List<UserEntity> list = this.list(qw);
        if (CollectionUtil.isNotEmpty(list)) {
            return CollectionUtil.getFirst(list);
        }
        return null;
    }

    /**
     * 获取
     *
     * @param realName 姓名
     * @return List<UserEntity>
     */
    @Override
    public List<UserEntity> getByRealName(String realName) {
        LambdaQueryWrapper<UserEntity> qw = Wrappers.<UserEntity>lambdaQuery()
        .eq(UserEntity::getRealName, realName);
        return this.list(qw);
    }

    /**
     * 批量插入
     *
     * @param userEntityList 用户
     */
    public void insertBatch(List<UserEntity> userEntityList) {
        this.baseMapper.insertBatch(userEntityList);
    }

    /**
     * 删除，物理删除
     *
     * @param id ID 主键
     */
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 批量删除，物理删除
     *
     * @param idList ID 主键 List
     */
    public void deleteByIds(List<Long> idList) {
        this.baseMapper.deleteByIds(idList);
    }
}
