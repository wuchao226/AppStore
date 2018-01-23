package com.wuchao.store.mvp.view.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuchao.store.R;
import com.wuchao.store.base.BaseMvpActivity;
import com.wuchao.store.mvp.presenter.impl.CategorySubjectPresenterImpl;
import com.wuchao.store.mvp.view.view.CategorySubjectView;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CategorySubjectActivity extends BaseMvpActivity<CategorySubjectPresenterImpl> implements CategorySubjectView {

    @BindView(R.id.title_text)
    TextView title_text ;
    @BindView(R.id.iv_search)
    ImageView iv_search ;
    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    public CategorySubjectPresenterImpl categorySubjectPresenter ;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_subject);
    }

    @Override
    protected void initView() {

        //设置沉浸式状态栏
        setStatusBar();
        iv_search.setVisibility(View.VISIBLE);
        //设置沉浸式状态栏背景
        title_bar.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("专题列表");
    }

    @Override
    protected void initData() {
        super.initData();
        categorySubjectPresenter.getCategorySubjectData(this);
    }

    @Override
    protected CategorySubjectPresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return categorySubjectPresenter;
    }

    @Override
    public void onCategorySubjectDataSuccess(List<String> list) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        SubjectAdapter adapter = new SubjectAdapter(this) ;
        adapter.addDataAll(list);
        rv.setAdapter(adapter);
    }

    @Override
    public void onCategorySubjectDataError(String msg) {

    }

    class SubjectAdapter extends CommonAdapter<String> {
        public SubjectAdapter(Context context) {
            super(context, R.layout.subject_item);
        }
        @Override
        protected void convert(ViewHolder holder, String url, int position) {
            holder.setImageUrl(R.id.item_icon,url);
        }
    }
}
