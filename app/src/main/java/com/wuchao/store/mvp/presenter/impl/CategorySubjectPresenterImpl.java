package com.wuchao.store.mvp.presenter.impl;


import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.mvp.interactor.CategorySubjectInteractor;
import com.wuchao.store.mvp.presenter.CategorySubjectPresenter;
import com.wuchao.store.mvp.view.view.CategorySubjectView;

import java.util.List;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubjectPresenterImpl extends BasePresenterImpl<CategorySubjectView> implements CategorySubjectPresenter {


    @Inject
    CategorySubjectInteractor categorySubjectInteractor ;

    @Inject
    public CategorySubjectPresenterImpl(){

    }

    @Override
    public void getCategorySubjectData(BaseActivity activity) {
        categorySubjectInteractor.loadCategorySubjectData(activity, new IGetDataDelegate<List<String>>() {
            @Override
            public void getDataSuccess(List<String> list) {
                mPresenterView.onCategorySubjectDataSuccess(list);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategorySubjectDataError(errmsg);
            }
        });
    }
}
