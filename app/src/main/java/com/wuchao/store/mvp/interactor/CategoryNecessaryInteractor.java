package com.wuchao.store.mvp.interactor;


import com.wuchao.store.api.CategoryNecessaryApi;
import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.CategoryNecessaryBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryNecessaryInteractor {

    private IGetDataDelegate<CategoryNecessaryBean> mDelegate ;

    @Inject
    public CategoryNecessaryInteractor(){

    }

    public void loadCategoryNecessaryData(BaseActivity activity, IGetDataDelegate<CategoryNecessaryBean> delegate){
        this.mDelegate = delegate ;
        CategoryNecessaryApi categoryNecessaryApi = new CategoryNecessaryApi(httpListener,activity);
        HttpManager httpListener = HttpManager.getInstance();
        httpListener.doHttpDeal(categoryNecessaryApi);
    }

    private HttpOnNextListener<CategoryNecessaryBean> httpListener = new HttpOnNextListener<CategoryNecessaryBean>() {
        @Override
        public void onNext(CategoryNecessaryBean categoryNecessaryBean) {
            mDelegate.getDataSuccess(categoryNecessaryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryNecessaryBean categoryNecessaryBean = JsonParseUtils.parseCategoryNecessaryBean(string);
            mDelegate.getDataSuccess(categoryNecessaryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
