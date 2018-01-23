package com.wuchao.store.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import com.wuchao.store.di.scope.ContextLife;
import com.wuchao.store.di.scope.PerFragment;

/**
 * @author: wuchao
 * @date: 2017/7/30 15:38
 * @desciption:
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return mFragment;
    }

    @Provides
    @PerFragment
    public Activity provideFragmentActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideFragmentContext() {
        return mFragment.getContext();
    }
}
