package com.wuchao.store.base.mvpbase;

/**
 * @author: wuchao
 * @date: 2017/7/30 16:36
 * @desciption: 因为所有的Presenter都会绑定view和解绑的操作，所以将其抽取到基类中
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected T mPresenterView;

    @Override
    public void attachView(T view) {
        this.mPresenterView = view;
    }

    @Override
    public void detachView() {
        this.mPresenterView = null;
    }
}
