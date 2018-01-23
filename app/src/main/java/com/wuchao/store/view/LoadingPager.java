package com.wuchao.store.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.wuchao.store.R;
import com.wuchao.store.utils.UIUtils;

/**
 * @author: wuchao
 * @date: 2017/7/21 16:34
 * @desciption: 先加顺序 load -->showPagerView-->createSuccessView
 * 在子类中 耗时操作放到 load中，然后load返回一个状态，在showPagerView中根据状态选择 显示的页面
 * 如果状态是成功的。那么就显示 createSuccessView
 */

public abstract class LoadingPager extends FrameLayout {

    /**
     * 默认状态
     */
    public static final int STATE_UNKNOW = 0;
    /**
     * 加载中
     */
    public static final int STATE_LOADING = 1;
    /**
     * 加载失败
     */
    public static final int STATE_ERROR = 2;
    /**
     * 空
     */
    public static final int STATE_EMPTY = 3;
    /**
     * 加载成功状态
     */
    public static final int STATE_SUCCESS = 4;
    /**
     * 当前状态
     */
    protected int state = STATE_UNKNOW;

    private View loadingView;//加载中界面
    private View errorView;//加载失败界面
    private View emotyView;//空界面
    private View successView;//加载成功界面

    public LoadingPager(@NonNull Context context) {
        this(context, null);
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 将所有界面添加到帧布局中
     */
    private void init() {
        this.setBackgroundColor(UIUtils.getColor(R.color.bg_page));
        loadingView = createLoadingView();
        if (loadingView != null) {
            this.addView(loadingView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView();
        if (errorView != null) {
            this.addView(errorView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        emotyView = createEmptyView();
        if (emotyView != null) {
            this.addView(emotyView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        showPager();
    }

    /**
     * 根据状态显示页面
     */
    private void showPager() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_LOADING || state == STATE_UNKNOW ? View.VISIBLE : View.GONE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
        }
        if (emotyView != null) {
            emotyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }
        if (state == STATE_SUCCESS && successView == null) {
            successView = createSuccessView();
            this.addView(successView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    /**
     * 显示布局
     */
    public void show() {
        if (state == STATE_EMPTY || state == STATE_ERROR) {
            state = STATE_UNKNOW;
        }
        if (state == STATE_UNKNOW) {
            load();
        }
        showPager();
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

    /**
     * 创建加载中界面
     *
     * @return
     */
    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page);
    }

    /**
     * 创建空界面
     *
     * @return
     */
    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    /**
     * 创建加载错误界面
     *
     * @return
     */
    private View createErrorView() {
        View view = UIUtils.inflate(R.layout.loading_error_page);
        Button settingButton = (Button) view.findViewById(R.id.setting);
        settingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入手机设置界面
            }
        });
        //点击刷新界面
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    /**
     * 服务器返回状态枚举
     */
    public enum LoadResult {
        error(STATE_ERROR), empty(STATE_EMPTY), success(STATE_SUCCESS);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public void setState(LoadResult result) {
        state = result.getValue();
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
    }

}
