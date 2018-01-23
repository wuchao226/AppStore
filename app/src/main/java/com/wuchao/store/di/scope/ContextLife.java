package com.wuchao.store.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author: wuchao
 * @date: 2017/7/30 15:36
 * @desciption: 限定符，限定Context
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {
    String value() default "";
}
