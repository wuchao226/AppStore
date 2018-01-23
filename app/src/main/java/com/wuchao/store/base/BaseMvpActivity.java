package com.wuchao.store.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.base.mvpbase.BaseView;

import com.wuchao.store.di.component.ActivityComponent;
import com.wuchao.store.di.component.DaggerActivityComponent;
import com.wuchao.store.di.module.ActivityModule;

/**
 * @author: wuchao
 * @date: 2017/7/30 16:41
 * @desciption: Activity实现MVP的基类
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected ActivityComponent mActivityComponent;
    protected T mPresenter;
    //通过Dagger2注入的是 presenter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        mPresenter = initInjector();
        //绑定view
        mPresenter.attachView(this);
        initData();
    }

    public void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(((StoreApplication) getApplication()).getAppComponent())
                .build();

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 完成注入并返回注入的Presenter对象
     *
     * @return
     */
    protected abstract T initInjector();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            //解除绑定
            mPresenter.detachView();
        }
    }
}
