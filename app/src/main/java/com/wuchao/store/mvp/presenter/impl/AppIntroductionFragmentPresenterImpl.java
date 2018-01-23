package com.wuchao.store.mvp.presenter.impl;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.AppIntroductionBean;
import com.wuchao.store.mvp.interactor.AppIntroductionInteractor;
import com.wuchao.store.mvp.presenter.AppIntroductionFragmentPresenter;
import com.wuchao.store.mvp.view.view.AppIntroductionFragmentView;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/9/22 18:43
 * @desciption:
 */

public class AppIntroductionFragmentPresenterImpl extends BasePresenterImpl<AppIntroductionFragmentView> implements AppIntroductionFragmentPresenter {

    @Inject
    AppIntroductionInteractor mInteractor;

    @Inject
    public AppIntroductionFragmentPresenterImpl() {
    }

    @Override
    public void getAppIntroductionData(BaseActivity activity, String packageName) {
        mInteractor.loadAppIntroduction(activity, packageName, new IGetDataDelegate<AppIntroductionBean>() {
            @Override
            public void getDataSuccess(AppIntroductionBean appIntroductionBean) {
                mPresenterView.onAppIntroductionDataSuccess(appIntroductionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppIntroductionDataError(errmsg);
            }
        });
    }
}
