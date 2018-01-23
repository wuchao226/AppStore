package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.AppRecommendApi;
import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.AppRecommendBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/9/29 10:56
 * @desciption:
 */

public class AppRecommendInteractor {

    private IGetDataDelegate<AppRecommendBean> mDelegate;

    @Inject
    public AppRecommendInteractor() {
    }

    public void loadAppRecommend(BaseActivity activity, String packageName, IGetDataDelegate<AppRecommendBean> delegate) {
        this.mDelegate = delegate;
        AppRecommendApi api = new AppRecommendApi(httpListener, activity, packageName);
        HttpManager httpListener = HttpManager.getInstance();
        httpListener.doHttpDeal(api);
    }

    private HttpOnNextListener<AppRecommendBean> httpListener = new HttpOnNextListener<AppRecommendBean>() {
        @Override
        public void onNext(AppRecommendBean appRecommendBean) {
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppRecommendBean appRecommendBean = JsonParseUtils.parseAppRecommendBean(string);
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
