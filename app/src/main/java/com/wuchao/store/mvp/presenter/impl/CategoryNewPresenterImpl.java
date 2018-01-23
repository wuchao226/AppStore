package com.wuchao.store.mvp.presenter.impl;


import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.CategoryNewBean;
import com.wuchao.store.mvp.interactor.CategoryNewInteractor;
import com.wuchao.store.mvp.presenter.CategoryNewPresenter;
import com.wuchao.store.mvp.view.view.CategoryNewView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryNewPresenterImpl extends BasePresenterImpl<CategoryNewView> implements CategoryNewPresenter {

    @Inject
    public CategoryNewInteractor categoryNewInteractor ;

    @Inject
    public CategoryNewPresenterImpl(){

    }

    @Override
    public void getCategoryNewData(BaseActivity activity) {
        categoryNewInteractor.loadCategoryNewData(activity, new IGetDataDelegate<CategoryNewBean>() {
            @Override
            public void getDataSuccess(CategoryNewBean categoryNewBean) {
                mPresenterView.onCategoryNewDataSuccess(categoryNewBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryNewDataError(errmsg);
            }
        });
    }
}
