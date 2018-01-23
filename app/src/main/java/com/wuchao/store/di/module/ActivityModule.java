package com.wuchao.store.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.wuchao.store.di.scope.ContextLife;
import com.wuchao.store.di.scope.PerActivity;

/**
 * @author: wuchao
 * @date: 2017/7/30 15:38
 * @desciption:
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mActivity;
    }
}
