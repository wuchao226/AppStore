package com.wuchao.store.mvp.presenter;


import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.CategoryNewView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategoryNewPresenter extends BasePresenter<CategoryNewView> {
    void getCategoryNewData(BaseActivity activity);
}
