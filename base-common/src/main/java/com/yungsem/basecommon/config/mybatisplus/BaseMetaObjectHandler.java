package com.yungsem.basecommon.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yungsem.basecommon.pojo.entity.rbac.User;
import com.yungsem.basecommon.util.UserUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-28
 */
@Component
public class BaseMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = this.getFieldValByName("createTime", metaObject);
        Object updateTime = this.getFieldValByName("updateTime", metaObject);
        Object createUserCode = this.getFieldValByName("createUserCode", metaObject);
        Object createUserName = this.getFieldValByName("createUserName", metaObject);

        Date now = new Date();

        User loginUser = UserUtil.getLoginUser();

        if (createTime == null) {
            metaObject.setValue("createTime", now);
        }
        if (updateTime == null) {
            metaObject.setValue("updateTime", now);
        }
        if (createUserCode == null) {
            metaObject.setValue("createUserCode", loginUser.getCode());
        }
        if (createUserName == null) {
            metaObject.setValue("createUserName", loginUser.getRealName());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateUserCode = this.getFieldValByName("updateUserCode", metaObject);
        Object updateUserName = this.getFieldValByName("updateUserName", metaObject);

        Date now = new Date();

        User loginUser = UserUtil.getLoginUser();

        metaObject.setValue("updateTime", now);

        if (updateUserCode == null) {
            metaObject.setValue("updateUserCode", loginUser.getCode());
        }
        if (updateUserName == null) {
            metaObject.setValue("updateUserName", loginUser.getRealName());
        }
    }
}
