package com.wuchao.store.mvp.presenter.impl;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.RecommendBean;
import com.wuchao.store.mvp.interactor.RecommendInterator;
import com.wuchao.store.mvp.presenter.RecommendFragmentPresenter;
import com.wuchao.store.mvp.view.view.RecommendFragmentView;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/7/30 17:06
 * @desciption:
 */

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter {

    @Inject
    RecommendInterator mRecommendInterator;

    @Inject
    public RecommendPresenterImpl() {
    }

    @Override
    public void getRecommendData(BaseActivity activity) {
        //网络请求获取数据
        mRecommendInterator.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onRecomendDataSuccess(recommendBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onRecomendDataError(msg);
            }
        });
    }

    @Override
    public void getRecommendDataMore(BaseActivity activity) {
        //网络请求获取数据
        mRecommendInterator.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onRecomendDataMoreSuccess(recommendBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onRecomendDataError(msg);
            }
        });
    }
}
