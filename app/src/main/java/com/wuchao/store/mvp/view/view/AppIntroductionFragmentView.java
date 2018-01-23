package com.wuchao.store.mvp.view.view;

import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.AppIntroductionBean;

/**
 * @author: wuchao
 * @date: 2017/9/22 18:41
 * @desciption:
 */

public interface AppIntroductionFragmentView extends BaseView {
    void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean);

    void onAppIntroductionDataError(String msg);
}
