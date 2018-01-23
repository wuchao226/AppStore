package com.wuchao.store.mvp.presenter;


import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.CategorySubjectView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategorySubjectPresenter extends BasePresenter<CategorySubjectView> {
    void getCategorySubjectData(BaseActivity activity) ;
}
