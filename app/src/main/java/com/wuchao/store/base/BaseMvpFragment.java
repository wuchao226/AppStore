package com.wuchao.store.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.base.mvpbase.BaseView;

import com.wuchao.store.di.component.DaggerFragmentComponent;
import com.wuchao.store.di.component.FragmentComponent;
import com.wuchao.store.di.module.FragmentModule;

/**
 * @author: wuchao
 * @date: 2017/7/30 16:54
 * @desciption: 将Dagger2依赖注入和绑定view的操作提取出来
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    protected FragmentComponent mFragmentComponent;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        mPresenter = initInjector();
        mPresenter.attachView(this);
    }

    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .appComponent(((StoreApplication) getActivity().getApplication()).getAppComponent())
                .build();
    }

    /**
     * 完成依赖注入并返回注入的Presenter
     *
     * @return
     */
    protected abstract T initInjector();

    @Override
    public void showToast(String msg) {
        Toast.makeText(getHoldingActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
