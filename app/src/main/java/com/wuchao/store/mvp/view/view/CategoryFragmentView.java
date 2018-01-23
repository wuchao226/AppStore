package com.wuchao.store.mvp.view.view;

import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.CategoryBean;

/**
 * @author: wuchao
 * @date: 2017/8/16 15:02
 * @desciption:
 */

public interface CategoryFragmentView extends BaseView {

    void onCategoryDataSuccess(CategoryBean categoryBean);

    void onCategoryDataError(String msg);
}
