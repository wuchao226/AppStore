package com.wuchao.store.mvp.presenter;

import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.TopFragmentView;

/**
 * @author: wuchao
 * @date: 2017/8/16 22:39
 * @desciption:
 */

public interface TopFragmentPresenter extends BasePresenter<TopFragmentView> {
    /**
     * 获取排行数据
     *
     * @param activity
     */
    void getTopData(BaseActivity activity);
}
