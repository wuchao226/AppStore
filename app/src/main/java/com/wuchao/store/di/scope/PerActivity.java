package com.wuchao.store.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author: wuchao
 * @date: 2017/7/30 15:35
 * @desciption: 自定义scope限定作用域
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
