package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.AppCommentApi;
import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.AppCommentBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/9/26 16:15
 * @desciption:
 */

public class AppCommentFragmentInteractor {

    private IGetDataDelegate<AppCommentBean> mDelegate;

    @Inject
    public AppCommentFragmentInteractor() {

    }

    public void loadAppCommentData(BaseActivity activity, String packageName, IGetDataDelegate<AppCommentBean> delegate) {
        this.mDelegate = delegate;
        AppCommentApi api = new AppCommentApi(httpListener, activity, packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppCommentBean> httpListener = new HttpOnNextListener<AppCommentBean>() {
        @Override
        public void onNext(AppCommentBean appCommentBean) {
            mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppCommentBean appCommentBean = JsonParseUtils.parseAppCommentBean(string);
            mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
