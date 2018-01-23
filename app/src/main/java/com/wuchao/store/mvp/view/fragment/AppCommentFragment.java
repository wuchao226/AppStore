package com.wuchao.store.mvp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuchao.store.R;
import com.wuchao.store.adapter.section.AppCommentContactsSection;
import com.wuchao.store.adapter.top.AppCommentTopWrapper;
import com.wuchao.store.base.BaseMvpFragment;
import com.wuchao.store.bean.AppCommentBean;
import com.wuchao.store.mvp.presenter.impl.AppCommentFragmentPresenterImpl;
import com.wuchao.store.mvp.view.activity.AppDetailActivity;
import com.wuchao.store.mvp.view.view.AppCommentFragmentView;
import com.wuchao.store.utils.UIUtils;
import com.wuchao.store.view.LoadingPager;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppCommentFragment extends BaseMvpFragment<AppCommentFragmentPresenterImpl> implements AppCommentFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Inject
    AppCommentFragmentPresenterImpl appCommentFragmentPresenter;

    private String packageName;
    private AppCommentBean appCommentBean;
    private List<AppCommentBean.CommentsBean> hotList = new ArrayList<>();
    private List<AppCommentBean.CommentsBean> list = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity) getActivity()).getAppPackageName();
        show();
    }

    @Override
    protected void load() {
        appCommentFragmentPresenter.getAppCommentData(getHoldingActivity(), packageName);
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_comment);
        ButterKnife.bind(this, view);

        for (AppCommentBean.CommentsBean commentsBean : appCommentBean.getComments()) {
            //type为1是精彩评论
            if (commentsBean.getCommentType().equals("1")) {
                hotList.add(commentsBean);
            } else {
                list.add(commentsBean);
            }
        }

        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());

        if (hotList.size() > 0)
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(), "精彩评论", hotList));
        if (list.size() > 0)
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(), "全部评论", list));

        AppCommentTopWrapper appCommentTopWrapper = new AppCommentTopWrapper(getContext(), sectionAdapter);
        appCommentTopWrapper.addDataAll(appCommentBean);
        rv.setAdapter(appCommentTopWrapper);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onAppCommentDataSuccess(AppCommentBean appCommentBean) {
        this.appCommentBean = appCommentBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppCommentDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected AppCommentFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return appCommentFragmentPresenter;
    }
}
