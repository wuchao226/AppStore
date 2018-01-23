package com.wuchao.store.mvp.presenter;

import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.base.mvpbase.BasePresenter;
import com.wuchao.store.mvp.view.view.RecommendFragmentView;

/**
 * @author: wuchao
 * @date: 2017/7/30 17:05
 * @desciption:
 */

public interface RecommendFragmentPresenter extends BasePresenter<RecommendFragmentView> {
    /**
     * 获取推荐数据
     */
    void getRecommendData(BaseActivity activity);

    /**
     * 获取更多推荐数据
     */
    void getRecommendDataMore(BaseActivity activity);
}

