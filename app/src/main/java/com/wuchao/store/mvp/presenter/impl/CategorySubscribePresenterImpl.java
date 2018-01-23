package com.wuchao.store.mvp.presenter.impl;


import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.CategorySubscribeBean;
import com.wuchao.store.mvp.interactor.CategorySubscribeInteractor;
import com.wuchao.store.mvp.presenter.CategorySubscribePresenter;
import com.wuchao.store.mvp.view.view.CategorySubscribeView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubscribePresenterImpl extends BasePresenterImpl<CategorySubscribeView> implements CategorySubscribePresenter {

    @Inject
    public CategorySubscribeInteractor categorySubscribeInteractor ;

    @Inject
    public CategorySubscribePresenterImpl(){

    }

    @Override
    public void getCategorySubscribeData(BaseActivity activity) {
        categorySubscribeInteractor.loadCategorySubscribeData(activity, new IGetDataDelegate<CategorySubscribeBean>() {
            @Override
            public void getDataSuccess(CategorySubscribeBean categorySubscribeBean) {
                mPresenterView.onCategorySubscribeDataSuccess(categorySubscribeBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategorySubscribeDataError(errmsg);
            }
        });
    }
}
