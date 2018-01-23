package com.wuchao.store.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuchao.store.R;
import com.wuchao.store.base.BaseMvpActivity;
import com.wuchao.store.bean.AppBean;
import com.wuchao.store.bean.CategorySubscribeBean;
import com.wuchao.store.mvp.presenter.impl.CategorySubscribePresenterImpl;
import com.wuchao.store.mvp.view.view.CategorySubscribeView;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import javax.inject.Inject;

import butterknife.BindView;

public class CategorySubscribeActivity extends BaseMvpActivity<CategorySubscribePresenterImpl> implements CategorySubscribeView {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Inject
    public CategorySubscribePresenterImpl categorySubscribePresenter;
    private CategorySubscribeBean mCategorySubscribeBean;


    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_subscribe);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatusBar();
        iv_search.setVisibility(View.VISIBLE);
        //设置沉浸式状态栏背景
        title_bar.setBackgroundResource(R.color.black_alpha_5);

        title_text.setText("预约");
    }

    @Override
    protected void initData() {
        super.initData();
        categorySubscribePresenter.getCategorySubscribeData(this);
    }

    @Override
    protected CategorySubscribePresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return categorySubscribePresenter;
    }

    @Override
    public void onCategorySubscribeDataSuccess(CategorySubscribeBean categorySubscribeBean) {
        this.mCategorySubscribeBean = categorySubscribeBean;
        rv.setLayoutManager(new LinearLayoutManager(this));
        CategorySubscribeAdapter adapter = new CategorySubscribeAdapter(this);
        adapter.addDataAll(categorySubscribeBean.getAppBeanList());
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                System.out.println("detailId:" + mCategorySubscribeBean.getAppBeanList().get(position).getDetailId());
                Intent intent = new Intent(CategorySubscribeActivity.this, WebViewActivity.class);
                intent.putExtra("name", mCategorySubscribeBean.getAppBeanList().get(position).getName());
                intent.putExtra("url", mCategorySubscribeBean.getAppBeanList().get(position).getDetailId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                return false;
            }
        });


    }

    @Override
    public void onCategorySubscribeDataError(String msg) {

    }

    public class CategorySubscribeAdapter extends CommonAdapter<AppBean> {

        public CategorySubscribeAdapter(Context context) {
            super(context, R.layout.applistitem_recommend);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            holder.setText(R.id.appTitle, appBean.getName());
            holder.setText(R.id.app_size, appBean.getSizeDesc());
            holder.setText(R.id.app_des, appBean.getMemo());
            holder.setImageUrl(R.id.appicon, appBean.getIcon());
        }
    }
}
