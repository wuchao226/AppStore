package com.wuchao.store.mvp.presenter.impl;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.AppCommentBean;
import com.wuchao.store.mvp.interactor.AppCommentFragmentInteractor;
import com.wuchao.store.mvp.presenter.AppCommentFragmentPresenter;
import com.wuchao.store.mvp.view.view.AppCommentFragmentView;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/9/26 16:12
 * @desciption:
 */

public class AppCommentFragmentPresenterImpl extends BasePresenterImpl<AppCommentFragmentView> implements AppCommentFragmentPresenter {

    @Inject
    AppCommentFragmentInteractor appCommentFragmentInteractor;

    @Inject
    public AppCommentFragmentPresenterImpl() {

    }

    @Override
    public void getAppCommentData(BaseActivity activity, String packageName) {
        appCommentFragmentInteractor.loadAppCommentData(activity, packageName, new IGetDataDelegate<AppCommentBean>() {
            @Override
            public void getDataSuccess(AppCommentBean appCommentBean) {
                mPresenterView.onAppCommentDataSuccess(appCommentBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppCommentDataError(errmsg);
            }
        });
    }
}
