package com.wuchao.store.base;

import android.os.Handler;

import com.wuchao.store.BuildConfig;
import com.wuchao.store.di.component.AppComponent;
import com.wuchao.store.di.component.DaggerAppComponent;
import com.wuchao.store.di.module.AppModule;
import com.zhxu.library.RxRetrofitApp;
import com.zhxu.recyclerview.App;

/**
 * @author: wuchao
 * @date: 2017/7/20 22:59
 * @desciption:
 */

public class StoreApplication extends App {

    private static StoreApplication context;
    private static Handler mHandler;
    private static int mMainThreadId;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mHandler = new Handler();
        initApplicationComponent();
        RxRetrofitApp.init(this, BuildConfig.DEBUG);
    }

    public static StoreApplication getContext() {
        return context;
    }

    private void initApplicationComponent() {//Dagger2
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 返回主线程的tid
     *
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 返回Handler
     *
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }
}
