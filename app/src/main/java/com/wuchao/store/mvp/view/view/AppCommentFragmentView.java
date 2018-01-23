package com.wuchao.store.mvp.view.view;

import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.AppCommentBean;

/**
 * @author: wuchao
 * @date: 2017/9/26 16:11
 * @desciption:
 */

public interface AppCommentFragmentView extends BaseView {
    void onAppCommentDataSuccess(AppCommentBean appCommentBean);

    void onAppCommentDataError(String msg);
}
