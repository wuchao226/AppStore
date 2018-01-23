package com.wuchao.store.mvp.presenter;

import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.CategoryFragmentView;

/**
 * @author: wuchao
 * @date: 2017/8/16 15:05
 * @desciption:
 */

public interface CategoryFragmentPresenter extends BasePresenter<CategoryFragmentView> {
    /**
     * 获取分类数据
     */
    void getCategoryData(BaseActivity activity);
}
