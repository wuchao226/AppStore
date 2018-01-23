package com.wuchao.store.mvp.presenter;

import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.AppCommentFragmentView;

/**
 * @author: wuchao
 * @date: 2017/9/26 16:12
 * @desciption:
 */

public interface AppCommentFragmentPresenter extends BasePresenter<AppCommentFragmentView> {
    void getAppCommentData(BaseActivity activity, String packageName);
}
