package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.AppDetailApi;
import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.AppDetailBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/9/21 15:22
 * @desciption:
 */

public class AppDetailInteractor {

    private IGetDataDelegate<AppDetailBean> mDelegate;

    @Inject
    public AppDetailInteractor() {
    }

    public void loadAppDetailData(BaseActivity activity, String packageName, IGetDataDelegate<AppDetailBean> delegate) {
        this.mDelegate = delegate;
        AppDetailApi api = new AppDetailApi(listener, activity, packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppDetailBean> listener = new HttpOnNextListener<AppDetailBean>() {
        @Override
        public void onNext(AppDetailBean appDetailBean) {
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppDetailBean appDetailBean = JsonParseUtils.parseAppDetailBean(string);
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
