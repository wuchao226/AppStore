package com.wuchao.store.mvp.view.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wuchao.store.R;
import com.wuchao.store.base.BaseActivity;
import com.wuchao.store.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GalleryActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.gallery)
    ViewPager mViewPager;
    @BindView(R.id.img_choose)
    LinearLayout imgViewGroup;

    private ImageView[] choose;
    private int curOffset = -1;

    private List<String> urlList;
    private int tag;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_gallery);
    }

    @Override
    protected void initView() {
        tag = getIntent().getIntExtra("tag", 0);
        curOffset = tag;
        urlList = getIntent().getStringArrayListExtra("urlList");

        showView();
    }

    private void showView() {
        choose = new ImageView[urlList.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        for (int i = 0, len = urlList.size(); i < len; i++) {
            choose[i] = new ImageView(this);
            choose[i].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_normal));
            if (curOffset == i) {
                choose[i].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_selected));
            }
            if (i < this.choose.length) {
                layoutParams.leftMargin = (int) (getResources().getDimension(R.dimen.detail_screen_point_margin));
            }
            choose[i].setLayoutParams(layoutParams);
            imgViewGroup.addView(this.choose[i]);
        }

        initViewPager();
    }

    private void initViewPager() {
        GalleryAdapter adapter = new GalleryAdapter(this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(curOffset);
        mViewPager.setOnPageChangeListener(this);
    }

    private void recycleImage(View view) {
        if (!(view instanceof ImageView)) {
            ImageView imageView = (ImageView) view;
            Drawable drawable = imageView.getDrawable();
            if (drawable == null || !(drawable instanceof BitmapDrawable)) {
                return;
            } else {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap == null || bitmap.isRecycled()) {
                    return;
                }
                bitmap.recycle();
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        choose[position].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_selected));
        choose[curOffset].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_normal));
        curOffset = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class GalleryAdapter extends PagerAdapter {

        private Context context = null;
        private List<View> viewList = new ArrayList();

        protected GalleryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return urlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View localView = (View) object;
            container.removeView(localView);
            recycleImage(localView);
            this.viewList.remove(localView);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(this.context);
            this.viewList.add(imageView);
            container.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            Glide.with(UIUtils.getContext()).load(urlList.get(position)).into(imageView);
            return imageView;
        }
    }

}
