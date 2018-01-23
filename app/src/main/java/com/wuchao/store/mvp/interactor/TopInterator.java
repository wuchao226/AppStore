package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.api.TopApi;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.TopBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/8/16 22:43
 * @desciption:
 */

public class TopInterator {

    private IGetDataDelegate<TopBean> delegate;

    @Inject
    public TopInterator() {
    }

    public void loadTopData(BaseActivity activity, IGetDataDelegate<TopBean> delegate) {
        this.delegate = delegate;
        TopApi topApi = new TopApi(listener, activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(topApi);
    }

    private HttpOnNextListener<TopBean> listener = new HttpOnNextListener<TopBean>() {
        @Override
        public void onNext(TopBean topBean) {
            delegate.getDataSuccess(topBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            TopBean topBean = JsonParseUtils.parseTopBean(string);
            delegate.getDataSuccess(topBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            delegate.getDataError(e.getMessage());
        }
    };
}
