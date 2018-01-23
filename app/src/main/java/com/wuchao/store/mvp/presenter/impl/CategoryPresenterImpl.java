package com.wuchao.store.mvp.presenter.impl;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenterImpl;
import com.wuchao.store.bean.CategoryBean;
import com.wuchao.store.mvp.interactor.CategoryInterator;
import com.wuchao.store.mvp.presenter.CategoryFragmentPresenter;
import com.wuchao.store.mvp.view.view.CategoryFragmentView;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/8/16 15:13
 * @desciption:
 */

public class CategoryPresenterImpl extends BasePresenterImpl<CategoryFragmentView> implements CategoryFragmentPresenter {

    @Inject
    CategoryInterator mCategoryInterator;

    @Inject
    public CategoryPresenterImpl() {
    }

    @Override
    public void getCategoryData(BaseActivity activity) {
        mCategoryInterator.loadCategoryData(activity, new IGetDataDelegate<CategoryBean>() {
            @Override
            public void getDataSuccess(CategoryBean categoryBean) {
                mPresenterView.onCategoryDataSuccess(categoryBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onCategoryDataError(msg);
            }
        });
    }
}
