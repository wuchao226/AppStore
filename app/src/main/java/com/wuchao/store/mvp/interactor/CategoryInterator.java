package com.wuchao.store.mvp.interactor;

import com.wuchao.store.api.CategoryApi;
import com.wuchao.store.api.IGetDataDelegate;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.bean.CategoryBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * @author: wuchao
 * @date: 2017/8/16 15:22
 * @desciption:
 */

public class CategoryInterator {

    private IGetDataDelegate<CategoryBean> delegate;

    @Inject
    public CategoryInterator() {
    }

    /**
     * 执行网络请求
     * 获取网络数据
     */
    public void loadCategoryData(BaseActivity activity, IGetDataDelegate<CategoryBean> delegate) {
        this.delegate = delegate;
        CategoryApi categoryApi = new CategoryApi(listener, activity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(categoryApi);
    }

    private HttpOnNextListener<CategoryBean> listener = new HttpOnNextListener<CategoryBean>() {
        @Override
        public void onNext(CategoryBean categoryBean) {
            delegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryBean categoryBean = JsonParseUtils.parseCategoryBean(string);
            delegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            delegate.getDataError(e.getMessage());
        }
    };

}
