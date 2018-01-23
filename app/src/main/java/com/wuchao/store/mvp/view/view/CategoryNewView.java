package com.wuchao.store.mvp.view.view;


import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.CategoryNewBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategoryNewView extends BaseView {
    void onCategoryNewDataSuccess(CategoryNewBean categoryNewBean);
    void onCategoryNewDataError(String msg);
}
