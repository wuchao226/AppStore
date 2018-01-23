package com.wuchao.store.mvp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuchao.store.R;
import com.wuchao.store.base.BaseMvpFragment;
import com.wuchao.store.bean.AppIntroductionBean;
import com.wuchao.store.mvp.presenter.impl.AppIntroductionFragmentPresenterImpl;
import com.wuchao.store.mvp.view.activity.AppDetailActivity;
import com.wuchao.store.mvp.view.activity.GalleryActivity;
import com.wuchao.store.mvp.view.view.AppIntroductionFragmentView;
import com.wuchao.store.utils.UIUtils;
import com.wuchao.store.view.LoadingPager;
import com.wuchao.store.view.widget.FlowLayout;
import com.wuchao.store.view.widget.FoldingTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppIntroductionFragment extends BaseMvpFragment<AppIntroductionFragmentPresenterImpl> implements AppIntroductionFragmentView, View.OnClickListener {

    @BindView(R.id.app_detail_gallery_container_linearlayout)
    LinearLayout mAppDetailGalleryContainerLinearlayout;
    @BindView(R.id.detail_appinfo_report_layout_linearlayout)
    LinearLayout mDetailAppinfoReportLayoutLinearlayout;
    @BindView(R.id.tariff_unit_textview)
    TextView mTariffUnitTextview;
    @BindView(R.id.size_unit_textview)
    TextView mSizeUnitTextview;
    @BindView(R.id.version_unit_textview)
    TextView mVersionUnitTextview;
    @BindView(R.id.release_unit_textview)
    TextView mReleaseUnitTextview;
    @BindView(R.id.develop_unit_textview)
    TextView mDevelopUnitTextview;
    @BindView(R.id.item_layout)
    LinearLayout mItemLayout;
    @BindView(R.id.detail_appinfo_tariff_textview)
    TextView mDetailAppinfoTariffTextview;
    @BindView(R.id.detail_appinfo_size_textview)
    TextView mDetailAppinfoSizeTextview;
    @BindView(R.id.detail_appinfo_version_textview)
    TextView mDetailAppinfoVersionTextview;
    @BindView(R.id.detail_appinfo_release_date_textview)
    TextView mDetailAppinfoReleaseDateTextview;
    @BindView(R.id.appdetail_appinfo_developer_textview)
    TextView mAppdetailAppinfoDeveloperTextview;
    @BindView(R.id.detail_desc_title_textview)
    TextView mDetailDescTitleTextview;
    @BindView(R.id.flowLayout)
    FlowLayout mFlowLayout;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.app_introduction_scrollview)
    ScrollView mAppIntroductionScrollview;

    @Inject
    public AppIntroductionFragmentPresenterImpl mPresenter;

    private AppIntroductionBean mAppIntroductionBean;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean) {
        mAppIntroductionBean = appIntroductionBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppIntroductionDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected AppIntroductionFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return mPresenter;
    }

    @Override
    protected void load() {
        mPresenter.getAppIntroductionData(mActivity, ((AppDetailActivity) getActivity()).getAppPackageName());
    }

    @Override
    protected View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_introduction);
        ButterKnife.bind(this, view);
        for (int i = 0; i < mAppIntroductionBean.getImageCompressList().size(); i++) {
            String url = mAppIntroductionBean.getImageCompressList().get(i);
            View screenView = LayoutInflater.from(getHoldingActivity()).inflate(R.layout.appdetail_item_screen_image, null);
            ImageView screenImageView = (ImageView) screenView.findViewById(R.id.appdetail_screen_img_imageview);
            screenImageView.setContentDescription(screenImageView.getResources().getString(R.string.appdetail_screenshot));
            screenImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            screenView.setOnClickListener(this);
            screenView.setTag(i);
            Glide.with(UIUtils.getContext()).load(url).into(screenImageView);
            mAppDetailGalleryContainerLinearlayout.addView(screenView);
        }

        mDetailAppinfoTariffTextview.setText(mAppIntroductionBean.getAppInfoBean().getTariffDesc());
        mDetailAppinfoSizeTextview.setText(Formatter.formatFileSize(getContext(), Long.parseLong(mAppIntroductionBean.getAppInfoBean().getSize())));
        mDetailAppinfoReleaseDateTextview.setText(mAppIntroductionBean.getAppInfoBean().getReleaseDate());
        mDetailAppinfoVersionTextview.setText(mAppIntroductionBean.getAppInfoBean().getVersion());
        mAppdetailAppinfoDeveloperTextview.setText(mAppIntroductionBean.getAppInfoBean().getDeveloper());

        for (int i = 0; i < mAppIntroductionBean.getAppDetailInfoBeanList().size(); i++) {
            FoldingTextView foldingTextView = new FoldingTextView(getContext());
            foldingTextView.setTitle(mAppIntroductionBean.getAppDetailInfoBeanList().get(i).getTitle());
            foldingTextView.setContent(mAppIntroductionBean.getAppDetailInfoBeanList().get(i).getBody());
            mLl.addView(foldingTextView);
        }

        List<String> tagList = mAppIntroductionBean.getTagList();
        for(int i = 0 ; i < tagList.size() ; i ++){
            View labView = UIUtils.inflate(R.layout.appdetail_item_label_item) ;
            TextView tv = (TextView) labView.findViewById(R.id.appdetail_label_content_textview);
            tv.setText(tagList.get(i));
            mFlowLayout.addView(labView);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        List<String> images = mAppIntroductionBean.getImagesList();
        Intent intent = new Intent(getContext(), GalleryActivity.class);
        intent.putExtra("tag",tag) ;
        intent.putStringArrayListExtra("urlList", (ArrayList<String>) images);
        getActivity().startActivity(intent) ;
    }
}
