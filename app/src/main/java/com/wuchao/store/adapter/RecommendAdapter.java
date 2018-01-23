package com.wuchao.store.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.wuchao.store.R;
import com.wuchao.store.bean.AppBean;
import com.wuchao.store.bean.RecommendBean;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ItemViewDelegate;
import com.zhxu.recyclerview.base.ViewHolder;

/**
 * @author: wuchao
 * @date: 2017/8/16 23:03
 * @desciption:
 */

public class RecommendAdapter extends MultiItemTypeAdapter<RecommendBean.RecommendAppBean> {

    private Context mContext;
    private AppItemListener mListener;

    public RecommendAdapter(Context context) {
        super(context);
        mContext = context;
        addItemViewDelegate(new AppDelegate());
        addItemViewDelegate(new AdDelegate());
    }

    public void setAppItemListener(AppItemListener listener) {
        mListener = listener;
    }

    public interface AppItemListener {
        void goAppDetail(String packageName);
    }

    /**
     * APP横向列表类型
     */
    public class AppDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_applist_horizontal;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 0;
        }

        @Override
        public void convert(ViewHolder holder, final RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setText(R.id.tv_item_title, recommendAppBean.getTitle());
            RecyclerView rv = holder.getView(R.id.rv_applist_item);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
            rv.setLayoutManager(linearLayoutManager);
            AppItemAdapter adapter = new AppItemAdapter(mContext);
            adapter.addDataAll(recommendAppBean.getAppList());
            rv.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    if (mListener != null) {
                        mListener.goAppDetail(recommendAppBean.getAppList().get(position).getPackageName());
                    }
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    return false;
                }
            });

            //更多按钮的点击事件
            holder.setOnClickListener(R.id.more_btn, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public class AdDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_ad;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 1;
        }

        @Override
        public void convert(ViewHolder holder, RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setImageUrl(R.id.iv_ad1, recommendAppBean.getIconList().get(0));
            holder.setImageUrl(R.id.iv_ad2, recommendAppBean.getIconList().get(1));
        }
    }

    public class AppItemAdapter extends CommonAdapter<AppBean> {

        public AppItemAdapter(Context context) {
            super(context, R.layout.item_app);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            holder.setImageUrl(R.id.iv_app_icon, appBean.getIcon());
            holder.setText(R.id.tv_app_name, appBean.getName());
        }
    }
}
