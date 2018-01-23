package com.wuchao.store.mvp.presenter;

import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.CategoryToolActivityView;

/**
 * @author: wuchao
 * @date: 2017/10/16 18:52
 * @desciption:
 */

public interface CategoryToolActivityPresenter extends BasePresenter<CategoryToolActivityView> {
    void getCategoryToolData(BaseActivity activity);
}
