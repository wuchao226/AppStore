package com.wuchao.store.mvp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuchao.store.R;
import com.wuchao.store.adapter.section.AppRecommendHotSection;
import com.wuchao.store.adapter.section.AppRecommendPopularSection;
import com.wuchao.store.adapter.section.AppRecommendTasteSection;
import com.wuchao.store.base.BaseMvpFragment;
import com.wuchao.store.bean.AppRecommendBean;
import com.wuchao.store.mvp.presenter.impl.AppRecommendFragmentPresenterImpl;
import com.wuchao.store.mvp.view.activity.AppDetailActivity;
import com.wuchao.store.mvp.view.view.AppRecommendFragmentView;
import com.wuchao.store.utils.UIUtils;
import com.wuchao.store.view.LoadingPager;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppRecommendFragment extends BaseMvpFragment<AppRecommendFragmentPresenterImpl> implements AppRecommendFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Inject
    AppRecommendFragmentPresenterImpl appRecommendFragmentPresenter;

    private String packageName;

    private AppRecommendBean appRecommendBean;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity) getActivity()).getAppPackageName();
        show();
    }

    @Override
    protected void load() {
        appRecommendFragmentPresenter.getAppRecommendData(getHoldingActivity(), packageName);
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_recommend);
        ButterKnife.bind(this,view);

        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
        adapter.addSection(new AppRecommendPopularSection(getContext(),"流行应用",appRecommendBean.getPopularAppBeanList(),packageName));
        adapter.addSection(new AppRecommendTasteSection(getContext(),"兴趣相近的用户也安装了",appRecommendBean.getTasteAppBeanList(),packageName));
        adapter.addSection(new AppRecommendHotSection(getContext(),"本周热议的社区应用",appRecommendBean.getHotAppBeanList(),packageName));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean) {
        this.appRecommendBean = appRecommendBean ;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppRecommendDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected AppRecommendFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return appRecommendFragmentPresenter;
    }
}
