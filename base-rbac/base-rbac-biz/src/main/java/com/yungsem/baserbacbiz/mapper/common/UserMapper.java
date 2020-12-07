package com.yungsem.baserbacbiz.mapper.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yungsem.basecommon.pojo.entity.rbac.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 *
 * @author yangsen
 * @version 1.0
 * @since 2020-08-21
 */
public interface UserMapper extends BaseMapper<User> {

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
    void deleteById(@Param("id") Long id);

    /**
     * 批量删除，物理删除
     *
     * @param idList ID 主键 List
     */
    void deleteByIds(@Param("idList") List<Long> idList);
}
