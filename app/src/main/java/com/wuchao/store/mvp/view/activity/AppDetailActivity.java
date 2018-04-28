package com.wuchao.store.mvp.view.activity;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuchao.store.R;
import com.wuchao.store.adapter.AppDetailPagerAdapter;
import com.wuchao.store.base.BaseMvpActivity;
import com.wuchao.store.bean.AppDetailBean;
import com.wuchao.store.mvp.presenter.impl.AppDetailPresenterImpl;
import com.wuchao.store.mvp.view.fragment.AppCommentFragment;
import com.wuchao.store.mvp.view.fragment.AppIntroductionFragment;
import com.wuchao.store.mvp.view.fragment.AppRecommendFragment;
import com.wuchao.store.mvp.view.view.AppDetailView;
import com.wuchao.store.utils.AppInfoUtils;
import com.wuchao.store.utils.UIUtils;
import com.wuchao.store.view.widget.DetailShareButton;
import com.wuchao.store.view.widget.DownloadProgressButton;
import com.wuchao.store.view.widget.SubTabNavigator;
import com.zhxu.library.download.DownInfo;
import com.zhxu.library.download.DownState;
import com.zhxu.library.download.HttpDownManager;
import com.zhxu.library.utils.DbDownUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AppDetailActivity extends BaseMvpActivity<AppDetailPresenterImpl> implements AppDetailView, HttpDownManager.DownloadObserver {

    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.bar_layout)
    RelativeLayout mBarLayout;
    @BindView(R.id.detail_download_button)
    DownloadProgressButton mDetailDownloadButton;
    @BindView(R.id.download_framelayout)
    FrameLayout mDownloadFramelayout;
    @BindView(R.id.detail_download_share_button)
    DetailShareButton mDetailDownloadShareButton;
    @BindView(R.id.detail_download_share_textview)
    TextView mDetailDownloadShareTextview;
    @BindView(R.id.detail_download_share_sub_layout_linearlayout)
    LinearLayout mDetailDownloadShareSubLayoutLinearlayout;
    @BindView(R.id.detail_download_cancel_imageview)
    ImageView mDetailDownloadCancelImageview;
    @BindView(R.id.detail_download_cancel_button_linearlayout)
    LinearLayout mDetailDownloadCancelButtonLinearlayout;
    @BindView(R.id.detail_download_share_placeholder_view)
    View mDetailDownloadSharePlaceholderView;
    @BindView(R.id.detail_download_share_layout_linearlayout)
    LinearLayout mDetailDownloadShareLayoutLinearlayout;
    @BindView(R.id.detail_comment_imageview)
    ImageView mDetailCommentImageview;
    @BindView(R.id.detail_download_comment_button_linearlayout)
    LinearLayout mDetailDownloadCommentButtonLinearlayout;
    @BindView(R.id.detail_download_comment_placeholder_view)
    View mDetailDownloadCommentPlaceholderView;
    @BindView(R.id.detail_download_comment_layout_linearlayout)
    LinearLayout mDetailDownloadCommentLayoutLinearlayout;
    @BindView(R.id.detail_download_button_layout_relativelayout)
    RelativeLayout mDetailDownloadButtonLayoutRelativelayout;
    @BindView(R.id.item_download)
    FrameLayout mItemDownload;
    @BindView(R.id.detail_head_app_icon_imageview)
    ImageView mDetailHeadAppIconImageview;
    @BindView(R.id.detail_head_app_name_textview)
    TextView mDetailHeadAppNameTextview;
    @BindView(R.id.detail_head_download_count_textview)
    TextView mDetailHeadDownloadCountTextview;
    @BindView(R.id.detail_head_app_stars_ratingbar)
    RatingBar mDetailHeadAppStarsRatingbar;
    @BindView(R.id.detail_head_label_icon_layout_linearlayout)
    LinearLayout mDetailHeadLabelIconLayoutLinearlayout;
    @BindView(R.id.detail_head_lable_folding_textview)
    TextView mDetailHeadLableFoldingTextview;
    @BindView(R.id.detail_head_label_layout_linearlayout)
    RelativeLayout mDetailHeadLabelLayoutLinearlayout;
    @BindView(R.id.detail_head_safe_icon_container_linearlayout)
    LinearLayout mDetailHeadSafeIconContainerLinearlayout;
    @BindView(R.id.detail_head_safe_icon_layout_linearlayout)
    LinearLayout mDetailHeadSafeIconLayoutLinearlayout;
    @BindView(R.id.subTab)
    SubTabNavigator mSubTab;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Inject
    AppDetailPresenterImpl mAppDetailPresenter;

    private String packageName;

    private AppDetailBean appDetailBean;
    protected boolean expand = false;

    private List<Fragment> fragments = null;

    private DbDownUtil dbUtil;
    private HttpDownManager manager;
    private DownInfo downInfo;
    private File outputFile;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_app_detail);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatusBar();
        mIvSearch.setVisibility(View.VISIBLE);
        //设置沉浸式状态栏背景
        if (title_bar != null)
            title_bar.setBackgroundResource(R.color.black_alpha_5);
        mTitleText.setText(getResources().getString(R.string.title_activity_app_detail));
    }

    @Override
    protected void initData() {
        super.initData();
        manager = HttpDownManager.getInstance();
        manager.registerObserver(this);
        dbUtil = DbDownUtil.getInstance();
        packageName = getIntent().getStringExtra("packageName");
        downInfo = dbUtil.queryDownBy(packageName.hashCode());
        if (downInfo == null) {
            outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), packageName + ".apk");
        }
        mAppDetailPresenter.getAppDetailData(this, packageName);
    }

    @Override
    protected AppDetailPresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return mAppDetailPresenter;
    }

    @Override
    public void onAppDetailDataSuccess(AppDetailBean appDetailBean) {
        this.appDetailBean = appDetailBean;
        setDetailHead();
    }

    @Override
    public void onAppDetailDataError(String msg) {
        setContentView(R.layout.loading_page_empty);
    }

    @Override
    public void onDownloadStateChanged(DownInfo info) {

    }

    @Override
    public void onDownloadProgressed(DownInfo info) {
        if(downInfo != null && info.getId() == downInfo.getId()) {
            mDetailDownloadButton.setProgress((int) (100 * info.getReadLength() / info.getCountLength()));
        }
    }

    private void setDetailHead() {
        Glide.with(UIUtils.getContext()).load(appDetailBean.getIcoUrl()).into(mDetailHeadAppIconImageview);
        mDetailHeadAppNameTextview.setText(appDetailBean.getName());
        mDetailHeadDownloadCountTextview.setText(appDetailBean.getIntro());
        mDetailHeadAppStarsRatingbar.setRating(Float.parseFloat(appDetailBean.getStars()));
        setLabel();
        setSafeLabel();
        setSubTab();
        setDetailDown();
        mDetailHeadLabelLayoutLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expand){
                    expand = false ;
                    mDetailHeadSafeIconLayoutLinearlayout.setVisibility(View.GONE);
                    mDetailHeadLableFoldingTextview.setBackgroundResource(R.drawable.ic_public_arrow_down);
                }else {
                    expand = true ;
                    mDetailHeadSafeIconLayoutLinearlayout.setVisibility(View.VISIBLE);
                    mDetailHeadLableFoldingTextview.setBackgroundResource(R.drawable.ic_public_arrow_up);
                }
            }
        });
    }

    private void setLabel() {
        for (AppDetailBean.LabelName labelNamesBean : appDetailBean.getLabelNameList()) {
            View labelView = UIUtils.inflate(R.layout.appdetail_item_head_label_item);
            TextView label = (TextView) labelView.findViewById(R.id.appdetail_head_label_textview);
            label.setText(labelNamesBean.getName());
            if (labelNamesBean.getType() == 1) {
                label.setTextColor(getResources().getColor(R.color.app_not_safe_textcolor));
            }
            mDetailHeadLabelIconLayoutLinearlayout.addView(labelView);
        }
    }

    private void setSafeLabel() {
        for (AppDetailBean.SafeLabel safeLabelsBean : appDetailBean.getSafeLabelList()) {
            View safeLabelView = UIUtils.inflate(R.layout.appdetail_item_head_safe_item);
            TextView safe_checker = (TextView) safeLabelView.findViewById(R.id.safe_checker_textview);
            TextView safe_checker_label = (TextView) safeLabelView.findViewById(R.id.safe_checker_label);
            ImageView detail_head_app_icon = (ImageView) safeLabelView.findViewById(R.id.detail_head_app_icon_imageview);
            TextView detail_safe_desc_textview = (TextView) safeLabelView.findViewById(R.id.detail_safe_desc_textview);

            safe_checker.setText(safeLabelsBean.getDetectorName());
            safe_checker_label.setText(safeLabelsBean.getDetectorDesc());
            Glide.with(UIUtils.getContext()).load(safeLabelsBean.getUrl()).into(detail_head_app_icon);
            detail_safe_desc_textview.setText(safeLabelsBean.getName());

            mDetailHeadSafeIconContainerLinearlayout.addView(safeLabelView);
        }
    }

    private void setSubTab() {
        fragments = new ArrayList<>();
        AppDetailPagerAdapter appDetailPagerAdapter = new AppDetailPagerAdapter(getSupportFragmentManager());
        AppIntroductionFragment appIntroductionFragment = new AppIntroductionFragment();
        AppCommentFragment appCommentFragment = new AppCommentFragment();
        AppRecommendFragment appRecommendFragment = new AppRecommendFragment();

        fragments.add(appIntroductionFragment);
        fragments.add(appCommentFragment);
        fragments.add(appRecommendFragment);

        appDetailPagerAdapter.setFragments(fragments);
        mViewPager.setAdapter(appDetailPagerAdapter);
        mViewPager.setOnPageChangeListener(mSubTab);
        mSubTab.setViewPager(mViewPager);

        mSubTab.setLeftText(appDetailBean.getTabInfoList().get(0));
        mSubTab.setNoneText(appDetailBean.getTabInfoList().get(1));
        mSubTab.setRightText(appDetailBean.getTabInfoList().get(2));
    }

    private void setDetailDown() {
        if (downInfo == null) {
            mDetailDownloadButton.setStartText("安装 " + Formatter.formatFileSize(UIUtils.getContext(),
                    Long.parseLong(appDetailBean.getSize())));
        } else {
            if (downInfo.getState() == DownState.DOWN) {
                mDetailDownloadButton.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                manager.startDown(downInfo);
            } else if (downInfo.getState() == DownState.PAUSE) {
                mDetailDownloadButton.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
            } else if (downInfo.getState() == DownState.FINISH) {
                mDetailDownloadButton.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
            }
            mDetailDownloadButton.setProgress((int) (100 * downInfo.getReadLength() / downInfo.getCountLength()));
        }

        mDetailDownloadButton.setStateChangeListener(new DownloadProgressButton.StateChangeListener() {
            @Override
            public void onPauseTask() {
                manager.pause(downInfo);
            }

            @Override
            public void onFinishTask() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //SystemClock.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AppInfoUtils.install(downInfo.getSavePath());
                                if (dbUtil != null && downInfo != null)
                                    dbUtil.update(downInfo);
                            }
                        });
                    }
                }).start();

            }

            @Override
            public void onLoadingTask() {
                mDetailDownloadButton.setMax(100);

                if (downInfo == null) {
                    downInfo = new DownInfo(appDetailBean.getDownloadUrl());
                    downInfo.setId((long) packageName.hashCode());
                    downInfo.setState(DownState.START);
                    downInfo.setSavePath(outputFile.getAbsolutePath());
                    dbUtil.save(downInfo);

                }
                if (downInfo.getState() != DownState.FINISH) {
                    manager.startDown(downInfo);
                }
            }
        });
    }

    public String getAppPackageName() {
        return packageName;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(manager != null){
            manager.unRegisterObserver(this);
            if(downInfo != null)
                dbUtil.update(downInfo);
        }
    }
}
