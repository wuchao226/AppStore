package com.wuchao.store.mvp.view.view;


import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.CategorySubscribeBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategorySubscribeView extends BaseView {
    void onCategorySubscribeDataSuccess(CategorySubscribeBean categorySubscribeBean) ;
    void onCategorySubscribeDataError(String msg);
}
