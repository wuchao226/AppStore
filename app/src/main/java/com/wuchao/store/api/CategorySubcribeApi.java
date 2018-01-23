package com.wuchao.store.api;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wuchao.store.bean.CategorySubscribeBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategorySubcribeApi extends BaseApi<CategorySubscribeBean> {
    public CategorySubcribeApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);

        setMothed("AppStore/categorydata/subscribe");

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategorySubscribeData();
    }

    @Override
    public CategorySubscribeBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseCategorySubscribeBean(string);
    }
}
