package com.yungsem.basecommon.config.annotation;

import java.lang.annotation.*;

/**
 * @author yangsen
 * @version 1.0
 * @since 2020-08-16
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {
}
