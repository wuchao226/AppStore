package com.wuchao.store.mvp.presenter.impl;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.TopBean;
import com.wuchao.store.mvp.interactor.TopInterator;
import com.wuchao.store.mvp.presenter.TopFragmentPresenter;
import com.wuchao.store.mvp.view.view.TopFragmentView;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/8/16 22:40
 * @desciption:
 */

public class TopFragmentPresenterImpl extends BasePresenterImpl<TopFragmentView> implements TopFragmentPresenter {

    @Inject
    TopInterator mTopInterator;

    @Inject
    public TopFragmentPresenterImpl() {
    }

    @Override
    public void getTopData(BaseActivity activity) {
        mTopInterator.loadTopData(activity, new IGetDataDelegate<TopBean>() {
            @Override
            public void getDataSuccess(TopBean topBean) {
                mPresenterView.onTopDataSuccess(topBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onTopDataError(msg);
            }
        });
    }
}
