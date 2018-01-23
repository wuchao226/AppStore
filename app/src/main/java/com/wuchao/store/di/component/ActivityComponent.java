package com.wuchao.store.di.component;

import android.app.Activity;
import android.content.Context;

import com.wuchao.store.di.module.ActivityModule;
import com.wuchao.store.di.scope.ContextLife;
import com.wuchao.store.di.scope.PerActivity;
import com.wuchao.store.mvp.view.activity.AppDetailActivity;
import com.wuchao.store.mvp.view.activity.CategoryNecessaryActivity;
import com.wuchao.store.mvp.view.activity.CategoryNewActivity;
import com.wuchao.store.mvp.view.activity.CategorySubjectActivity;
import com.wuchao.store.mvp.view.activity.CategorySubscribeActivity;
import com.wuchao.store.mvp.view.activity.CategoryToolActivity;
import com.wuchao.store.mvp.view.activity.HomeActivity;

import dagger.Component;

/**
 * @author: wuchao
 * @date: 2017/7/30 16:11
 * @desciption:
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomeActivity activity);

    void inject(AppDetailActivity activity);

    void inject(CategoryToolActivity activity);

    void inject(CategorySubscribeActivity activity);

    void inject(CategoryNecessaryActivity activity);

    void inject(CategoryNewActivity activity);

    void inject(CategorySubjectActivity activity);
}
