package com.wuchao.store.mvp.view.view;

import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.AppRecommendBean;

/**
 * @author: wuchao
 * @date: 2017/9/29 10:50
 * @desciption:
 */

public interface AppRecommendFragmentView extends BaseView {
    void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean);

    void onAppRecommendDataError(String msg);
}
