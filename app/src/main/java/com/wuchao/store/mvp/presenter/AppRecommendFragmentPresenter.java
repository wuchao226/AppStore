package com.wuchao.store.mvp.presenter;

import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.AppRecommendFragmentView;

/**
 * @author: wuchao
 * @date: 2017/9/29 10:52
 * @desciption:
 */

public interface AppRecommendFragmentPresenter extends BasePresenter<AppRecommendFragmentView> {
    void getAppRecommendData(BaseActivity activity, String packageName);
}
