package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.CategorySubcribeApi;
import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.CategorySubscribeBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubscribeInteractor {

    private IGetDataDelegate<CategorySubscribeBean> mDelegate ;

    @Inject
    public CategorySubscribeInteractor(){

    }

    public void loadCategorySubscribeData(BaseActivity activity, IGetDataDelegate<CategorySubscribeBean> delegate){
        this.mDelegate = delegate ;
        CategorySubcribeApi categorySubcribeApi = new CategorySubcribeApi(httpListener,activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categorySubcribeApi);
    }

    private HttpOnNextListener httpListener = new HttpOnNextListener<CategorySubscribeBean>(){

        @Override
        public void onNext(CategorySubscribeBean categorySubscribeBean) {
            mDelegate.getDataSuccess(categorySubscribeBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategorySubscribeBean categorySubscribeBean = JsonParseUtils.parseCategorySubscribeBean(string);
            mDelegate.getDataSuccess(categorySubscribeBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
