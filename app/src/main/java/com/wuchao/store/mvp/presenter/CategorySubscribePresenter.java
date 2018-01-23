package com.wuchao.store.mvp.presenter;


import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.CategorySubscribeView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategorySubscribePresenter extends BasePresenter<CategorySubscribeView> {
    void getCategorySubscribeData(BaseActivity activity);
}
