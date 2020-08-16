package com.yungsem.basecommon.pojo.entity.business;

import com.yungsem.basecommon.pojo.entity.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessEntity extends BaseEntity {
    private String biz;
}
