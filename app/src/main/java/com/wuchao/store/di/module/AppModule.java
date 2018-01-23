package com.wuchao.store.di.module;

import android.content.Context;

import com.wuchao.store.base.StoreApplication;

import dagger.Module;
import dagger.Provides;
import com.wuchao.store.di.scope.ContextLife;
import com.wuchao.store.di.scope.PerApp;

/**
 * @author: wuchao
 * @date: 2017/7/30 15:32
 * @desciption:
 */
@Module
public class AppModule {

    private StoreApplication mStoreApplication;

    public AppModule(StoreApplication storeApplication) {
        this.mStoreApplication = storeApplication;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideAppContext(){
        return mStoreApplication.getApplicationContext();
    }
}
