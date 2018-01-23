package com.wuchao.store.di.component;

import android.app.Activity;
import android.content.Context;

import com.wuchao.store.di.module.FragmentModule;
import com.wuchao.store.di.scope.ContextLife;
import com.wuchao.store.di.scope.PerFragment;
import com.wuchao.store.mvp.view.fragment.AppCommentFragment;
import com.wuchao.store.mvp.view.fragment.AppIntroductionFragment;
import com.wuchao.store.mvp.view.fragment.AppManagerFragment;
import com.wuchao.store.mvp.view.fragment.AppRecommendFragment;
import com.wuchao.store.mvp.view.fragment.CategoryFragment;
import com.wuchao.store.mvp.view.fragment.MyFragment;
import com.wuchao.store.mvp.view.fragment.RecommendFragment;
import com.wuchao.store.mvp.view.fragment.TopFragment;

import dagger.Component;

/**
 * @author: wuchao
 * @date: 2017/7/30 16:18
 * @desciption:
 */
@PerFragment
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(RecommendFragment fragment);

    void inject(CategoryFragment fragment);

    void inject(TopFragment fragment);

    void inject(MyFragment fragment);

    void inject(AppManagerFragment fragment);

    void inject(AppIntroductionFragment fragment);

    void inject(AppCommentFragment fragment);

    void inject(AppRecommendFragment fragment);
}
