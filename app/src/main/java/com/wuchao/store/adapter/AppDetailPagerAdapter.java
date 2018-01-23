package com.wuchao.store.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author: wuchao
 * @date: 2017/9/22 16:25
 * @desciption:
 */

public class AppDetailPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public void setFragments(List<Fragment> fragments) {
        this.mFragments = fragments;
    }

    public AppDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment=null;
        try {
            fragment= (Fragment) super.instantiateItem(container, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
