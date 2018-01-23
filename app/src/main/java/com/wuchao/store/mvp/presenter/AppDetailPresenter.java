package com.wuchao.store.mvp.presenter;

import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.AppDetailView;

/**
 * @author: wuchao
 * @date: 2017/9/21 10:27
 * @desciption:
 */

public interface AppDetailPresenter extends BasePresenter<AppDetailView> {
    void getAppDetailData(BaseActivity activity, String packageName);
}
