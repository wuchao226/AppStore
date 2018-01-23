package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.CategoryToolApi;
import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.CategoryToolBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryToolActivityInteractor {

    private IGetDataDelegate<CategoryToolBean> mDelegate ;

    @Inject
    public CategoryToolActivityInteractor(){

    }

    public void loadCategoryToolData(BaseActivity activity, IGetDataDelegate<CategoryToolBean> delegate){
        this.mDelegate = delegate ;

        CategoryToolApi categoryToolApi = new CategoryToolApi(httpListener,activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categoryToolApi);
    }

    private HttpOnNextListener httpListener = new HttpOnNextListener<CategoryToolBean>() {
        @Override
        public void onNext(CategoryToolBean categoryToolBean) {
            mDelegate.getDataSuccess(categoryToolBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryToolBean categoryToolBean = JsonParseUtils.parseCategoryToolBean(string);
            mDelegate.getDataSuccess(categoryToolBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
