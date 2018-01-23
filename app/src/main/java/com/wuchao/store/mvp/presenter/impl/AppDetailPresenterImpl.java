package com.wuchao.store.mvp.presenter.impl;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.AppDetailBean;
import com.wuchao.store.mvp.interactor.AppDetailInteractor;
import com.wuchao.store.mvp.presenter.AppDetailPresenter;
import com.wuchao.store.mvp.view.view.AppDetailView;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/9/21 15:19
 * @desciption:
 */

public class AppDetailPresenterImpl extends BasePresenterImpl<AppDetailView> implements AppDetailPresenter {

    @Inject
    AppDetailInteractor mAppDetailInteractor;

    @Inject
    public AppDetailPresenterImpl() {
    }

    @Override
    public void getAppDetailData(BaseActivity activity, String packageName) {
        mAppDetailInteractor.loadAppDetailData(activity, packageName, new IGetDataDelegate<AppDetailBean>() {
            @Override
            public void getDataSuccess(AppDetailBean appDetailBean) {
                mPresenterView.onAppDetailDataSuccess(appDetailBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onAppDetailDataError(msg);
            }
        });
    }
}
