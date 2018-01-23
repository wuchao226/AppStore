package com.wuchao.store.mvp.presenter;


import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.CategoryNecessaryView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategoryNecessaryPresenter extends BasePresenter<CategoryNecessaryView> {
    void getCategoryNecessaryData(BaseActivity activity) ;
}
