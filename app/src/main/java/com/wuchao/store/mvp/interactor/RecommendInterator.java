package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.api.RecommendApi;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.RecommendBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/8/14 23:05
 * @desciption:
 */

public class RecommendInterator {

    private IGetDataDelegate<RecommendBean> mDelegate;

    @Inject
    public RecommendInterator() {
    }

    /**
     * 执行网络请求
     * 获取网络数据
     */
    public void loadRecommendData(BaseActivity activity, IGetDataDelegate<RecommendBean> delegate) {
        this.mDelegate = delegate;
        RecommendApi recommendApi = new RecommendApi(listener, activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(recommendApi);
    }

    private HttpOnNextListener<RecommendBean> listener = new HttpOnNextListener<RecommendBean>() {
        @Override
        public void onNext(RecommendBean recommendBean) {
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            RecommendBean recommendBean = JsonParseUtils.parseRecommendBean(string);
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
