package com.wuchao.store.mvp.view.view;

import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.CategoryToolBean;

/**
 * @author: wuchao
 * @date: 2017/10/16 18:42
 * @desciption:
 */

public interface CategoryToolActivityView extends BaseView {
    void onCategoryToolDataSuccess(CategoryToolBean categoryToolBean);

    void onCategoryToolError(String msg);
}
