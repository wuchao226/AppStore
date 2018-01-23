package com.wuchao.store.mvp.view.view;

import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.TopBean;

/**
 * @author: wuchao
 * @date: 2017/8/16 22:38
 * @desciption:
 */

public interface TopFragmentView extends BaseView {

    void onTopDataSuccess(TopBean topBean);

    void onTopDataError(String msg);
}
