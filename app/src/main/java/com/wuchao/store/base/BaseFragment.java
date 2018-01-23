package com.wuchao.store.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuchao.store.view.LoadingPager;

/**
 * @author: wuchao
 * @date: 2017/7/20 22:36
 * @desciption:
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager mLoadingPager;

    protected BaseActivity mActivity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLoadingPager == null) {
            mLoadingPager = new LoadingPager(getContext()) {
                @Override
                protected void load() {
                    BaseFragment.this.load();
                }

                @Override
                protected View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }
            };
        }
        return mLoadingPager;
    }

    public void show() {
        if (mLoadingPager != null) {
            mLoadingPager.show();
        }
    }

    public void setState(LoadingPager.LoadResult result) {
        if (mLoadingPager != null) {
            mLoadingPager.setState(result);
        }
    }

    /**
     * 加载获取数据
     */
    protected abstract void load();

    /**
     * 加载成功页面
     *
     * @return
     */
    protected abstract View createSuccessView();
}
