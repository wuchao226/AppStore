package com.wuchao.store.mvp.view.view;

import com.wuchao.store.base.mvpbase.BaseView;
import com.wuchao.store.bean.RecommendBean;

/**
 * @author: wuchao
 * @date: 2017/7/30 17:02
 * @desciption:
 */

public interface RecommendFragmentView extends BaseView {

    void onRecomendDataSuccess(RecommendBean recommendBean);

    void onRecomendDataMoreSuccess(RecommendBean recommendBean);

    void onRecomendDataError(String msg);
}
