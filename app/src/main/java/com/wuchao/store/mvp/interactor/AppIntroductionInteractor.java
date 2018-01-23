package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.AppIntroductionApi;
import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.AppIntroductionBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/9/25 17:59
 * @desciption:
 */

public class AppIntroductionInteractor {

    private IGetDataDelegate<AppIntroductionBean> mDelegate;

    @Inject
    public AppIntroductionInteractor() {

    }

    public void loadAppIntroduction(BaseActivity activity, String packageName, IGetDataDelegate<AppIntroductionBean> delegate) {
        this.mDelegate = delegate ;
        AppIntroductionApi api = new AppIntroductionApi(httpListener,activity,packageName);
        HttpManager httpManager = HttpManager.getInstance() ;
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppIntroductionBean> httpListener = new HttpOnNextListener<AppIntroductionBean>() {
        @Override
        public void onNext(AppIntroductionBean appIntroductionBean) {
            mDelegate.getDataSuccess(appIntroductionBean);

        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppIntroductionBean appIntroductionBean = JsonParseUtils.parseAppIntroductionBean(string);
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
