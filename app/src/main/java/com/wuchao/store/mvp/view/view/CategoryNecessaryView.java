package com.wuchao.store.mvp.view.view;


import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.CategoryNecessaryBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategoryNecessaryView extends BaseView {
    void onCategoryNecessaryDataSuccess(CategoryNecessaryBean categoryNecessaryBean);
    void onCategoryNecessaryDataError(String msg) ;
}
