package com.wuchao.store.mvp.presenter;

import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.AppIntroductionFragmentView;

/**
 * @author: wuchao
 * @date: 2017/9/22 18:42
 * @desciption:
 */

public interface AppIntroductionFragmentPresenter extends BasePresenter<AppIntroductionFragmentView> {
    void getAppIntroductionData(BaseActivity activity, String packageName);
}
