package com.wuchao.store.mvp.presenter.impl;


import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.CategoryNecessaryBean;
import com.wuchao.store.mvp.interactor.CategoryNecessaryInteractor;
import com.wuchao.store.mvp.presenter.CategoryNecessaryPresenter;
import com.wuchao.store.mvp.view.view.CategoryNecessaryView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryNecessaryPresenterImpl extends BasePresenterImpl<CategoryNecessaryView> implements CategoryNecessaryPresenter {


    @Inject
    public CategoryNecessaryInteractor categoryNecessaryInteractor ;

    @Inject
    public CategoryNecessaryPresenterImpl(){

    }

    @Override
    public void getCategoryNecessaryData(BaseActivity activity) {
        categoryNecessaryInteractor.loadCategoryNecessaryData(activity, new IGetDataDelegate<CategoryNecessaryBean>() {
            @Override
            public void getDataSuccess(CategoryNecessaryBean categoryNecessaryBean) {
                mPresenterView.onCategoryNecessaryDataSuccess(categoryNecessaryBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryNecessaryDataError(errmsg);
            }
        });
    }
}
