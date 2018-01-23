package com.wuchao.store.di.component;

import android.content.Context;

import dagger.Component;
import com.wuchao.store.di.module.AppModule;
import com.wuchao.store.di.scope.ContextLife;
import com.wuchao.store.di.scope.PerApp;

/**
 * @author: wuchao
 * @date: 2017/7/30 16:02
 * @desciption: 提供全局单例的Context对象
 */
@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    Context getApplicationContext();
}
