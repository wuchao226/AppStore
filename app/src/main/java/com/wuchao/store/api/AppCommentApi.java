package com.wuchao.store.api;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wuchao.store.bean.AppCommentBean;
import com.wuchao.store.utils.JsonParseUtils;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author: wuchao
 * @date: 2017/9/26 16:13
 * @desciption:
 */

public class AppCommentApi extends BaseApi<AppCommentBean> {
    private String packageName;

    public AppCommentApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
        setMothed("AppStore/app/comment/" + packageName);
        this.packageName = packageName;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppCommentData(packageName);
    }

    @Override
    public AppCommentBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("string:" + string);
        return JsonParseUtils.parseAppCommentBean(string);
    }
}
