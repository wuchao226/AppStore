package com.wuchao.store.mvp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuchao.store.R;
import com.wuchao.store.adapter.RecommendAdapter;
import com.wuchao.store.adapter.top.RecommendTopWrapper;
import com.wuchao.store.base.BaseMvpFragment;
import com.wuchao.store.bean.RecommendBean;
import com.wuchao.store.mvp.presenter.impl.RecommendPresenterImpl;
import com.wuchao.store.mvp.view.activity.AppDetailActivity;
import com.wuchao.store.mvp.view.view.RecommendFragmentView;
import com.wuchao.store.utils.UIUtils;
import com.wuchao.store.view.LoadingPager;
import com.zhxu.recyclerview.pullrefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推荐页
 */
public class RecommendFragment extends BaseMvpFragment<RecommendPresenterImpl> implements RecommendFragmentView {

    private final static String TAG = "RecommendFragment";

    @Inject
    public RecommendPresenterImpl mRecommendPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ptr)
    PullToRefreshView mPtr;

    private RecommendBean mRecommendBean;
    private List<RecommendBean.RecommendAppBean> mRecommendAppBeanList = new ArrayList<>();
    private RecommendAdapter mAdapter;
    private RecommendTopWrapper topWrapper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected void load() {
        //发起网络请求
        mRecommendPresenter.getRecommendData(getHoldingActivity());
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtils.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this, inflate);
        mAdapter = new RecommendAdapter(getHoldingActivity());
        mAdapter.addDataAll(mRecommendBean.getRecommendAppBeanList());
        topWrapper = new RecommendTopWrapper(getHoldingActivity(), mAdapter);
        topWrapper.addDataAll(mRecommendBean.getBannerList());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        mRecyclerView.setAdapter(topWrapper);

        mAdapter.setAppItemListener(new RecommendAdapter.AppItemListener() {
            @Override
            public void goAppDetail(String packageName) {
                Intent intent = new Intent(mActivity, AppDetailActivity.class);
                intent.putExtra("packageName", packageName);
                mActivity.startActivity(intent);
            }
        });

        //不让其下拉刷新
        mPtr.setPullDownEnable(false);
        mPtr.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                mRecommendPresenter.getRecommendDataMore(mActivity);
            }
        });
        return inflate;
    }

    @Override
    public void onRecomendDataSuccess(RecommendBean recommendBean) {
        mRecommendBean = recommendBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onRecomendDataMoreSuccess(RecommendBean recommendBean) {
        mRecommendAppBeanList.addAll(recommendBean.getRecommendAppBeanList());
        mAdapter.clearData();
        mAdapter.addDataAll(mRecommendAppBeanList);
        topWrapper.notifyDataSetChanged();
        mPtr.onFinishLoading();
    }

    @Override
    public void onRecomendDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected RecommendPresenterImpl initInjector() {
        //完成依赖注入
        mFragmentComponent.inject(this);
        //返回注入的Presenter
        return mRecommendPresenter;
    }

}
