package com.wuchao.store.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author: wuchao
 * @date: 2017/7/30 15:36
 * @desciption:
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}
