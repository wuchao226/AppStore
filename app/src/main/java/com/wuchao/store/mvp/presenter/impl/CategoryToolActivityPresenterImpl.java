package com.wuchao.store.mvp.presenter.impl;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.CategoryToolBean;
import com.wuchao.store.mvp.interactor.CategoryToolActivityInteractor;
import com.wuchao.store.mvp.presenter.CategoryToolActivityPresenter;
import com.wuchao.store.mvp.view.view.CategoryToolActivityView;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/10/16 18:53
 * @desciption:
 */

public class CategoryToolActivityPresenterImpl extends BasePresenterImpl<CategoryToolActivityView> implements CategoryToolActivityPresenter {

    @Inject
    public CategoryToolActivityInteractor categoryToolActivityInteractor ;

    @Inject
    public CategoryToolActivityPresenterImpl(){

    }

    @Override
    public void getCategoryToolData(BaseActivity activity) {
        categoryToolActivityInteractor.loadCategoryToolData(activity, new IGetDataDelegate<CategoryToolBean>() {
            @Override
            public void getDataSuccess(CategoryToolBean categoryToolBean) {
                mPresenterView.onCategoryToolDataSuccess(categoryToolBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryToolError(errmsg);
            }
        });
    }
}
