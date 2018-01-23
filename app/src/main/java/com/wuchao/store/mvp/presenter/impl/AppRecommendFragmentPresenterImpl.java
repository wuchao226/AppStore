package com.wuchao.store.mvp.presenter.impl;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.AppRecommendBean;
import com.wuchao.store.mvp.interactor.AppRecommendInteractor;
import com.wuchao.store.mvp.presenter.AppRecommendFragmentPresenter;
import com.wuchao.store.mvp.view.view.AppRecommendFragmentView;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/9/29 10:52
 * @desciption:
 */

public class AppRecommendFragmentPresenterImpl extends BasePresenterImpl<AppRecommendFragmentView> implements AppRecommendFragmentPresenter {

    @Inject
    AppRecommendInteractor mAppRecommendInteractor;

    @Inject
    public AppRecommendFragmentPresenterImpl() {
    }

    @Override
    public void getAppRecommendData(BaseActivity activity, String packageName) {
        mAppRecommendInteractor.loadAppRecommend(activity, packageName, new IGetDataDelegate<AppRecommendBean>() {
            @Override
            public void getDataSuccess(AppRecommendBean appRecommendBean) {
                mPresenterView.onAppRecommendDataSuccess(appRecommendBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onAppRecommendDataError(msg);
            }
        });
    }
}
