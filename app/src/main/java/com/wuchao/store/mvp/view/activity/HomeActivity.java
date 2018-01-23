package com.wuchao.store.mvp.view.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wuchao.store.R;
import com.wuchao.store.adapter.FixPagerAdapter;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.factory.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.status_bar)
    LinearLayout mStatusBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.tab)
    LinearLayout mTab;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private FixPagerAdapter mAdapter;
    private String[] titles = {"推荐", "分类", "排行", "管理", "我的"};
    private List<Fragment> fragments;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final int statusHeight = getStatusBarHeight();
            mStatusBar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = mStatusBar.getHeight();
                    ViewGroup.LayoutParams params = mStatusBar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    mStatusBar.setLayoutParams(params);
                }
            });
        }
        initViewPagerFragment();
    }

    private void initViewPagerFragment() {
        mAdapter = new FixPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(FragmentFactory.createFragment(i));
        }
        mAdapter.setTitles(titles);
        mAdapter.setFragmnets(fragments);
        mViewpager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

}
