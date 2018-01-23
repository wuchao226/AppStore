package com.wuchao.store.mvp.view.view;

import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.AppDetailBean;

/**
 * @author: wuchao
 * @date: 2017/9/21 10:23
 * @desciption:
 */

public interface AppDetailView extends BaseView {
    void onAppDetailDataSuccess(AppDetailBean appDetailBean);

    void onAppDetailDataError(String msg);
}
