package com.wuchao.store.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.wuchao.store.banner.RecommendController;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * @author: wuchao
 * @date: 2017/8/23 22:12
 * @desciption:
 */

public class RecommendTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private RecommendController mController;

    public RecommendTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        mContext = context;
        mController = new RecommendController(mContext);
        addHeaderView(mController.getContentView());
    }

    public void addDataAll(List<String> list) {
        if (mController != null) {
            mController.setData(list);
        }
    }
}
