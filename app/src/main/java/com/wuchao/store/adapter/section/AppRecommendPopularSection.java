package com.wuchao.store.adapter.section;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wuchao.store.R;
import com.wuchao.store.bean.AppBean;
import com.wuchao.store.mvp.view.activity.AppDetailActivity;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;


/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendPopularSection extends StatelessSection {
    private Context mContext ;
    private String title ;
    private List<AppBean> popularAppBeanList;
    private String packageName ;

    public AppRecommendPopularSection(Context context, String title, List<AppBean> popularAppBeen,String packageName) {
        super(R.layout.appdetail_recommend_list_title,R.layout.applistitem_popular_recommend);
        this.mContext = context ;
        this.title = title ;
        this.popularAppBeanList = popularAppBeen ;
        this.packageName = packageName ;
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        AppItemAdapter appItemAdapter = new AppItemAdapter(mContext);
        appItemAdapter.addDataAll(popularAppBeanList);
        ((ItemViewHolder)holder).rv.setAdapter(appItemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ((ItemViewHolder)holder).rv.setLayoutManager(linearLayoutManager);

    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.titleText,title);
        holder.setOnClickListener(R.id.more_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, AppMoreRecommendActivity.class);
//                intent.putExtra("type","popular");
//                intent.putExtra("packageName",packageName);
//                ((AppDetailActivity)mContext).startActivity(intent);
            }
        });
    }


    public class AppItemAdapter extends CommonAdapter<AppBean> {

        public AppItemAdapter(Context context) {
            super(context, R.layout.item_app);
        }

        @Override
        protected void convert(ViewHolder holder, final AppBean popularAppBean, int position) {
            holder.setImageUrl(R.id.iv_app_icon,popularAppBean.getIcon()) ;
            holder.setText(R.id.tv_app_name,popularAppBean.getName()) ;
            holder.setText(R.id.tv_app_size,popularAppBean.getSizeDesc()) ;

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AppDetailActivity.class);
                    intent.putExtra("packageName",popularAppBean.getPackageName());
                    ((AppDetailActivity)mContext).startActivity(intent);
                }
            });
        }
    }

    public class TitleViewHolder extends ViewHolder {
        TextView tvTitle;
        TextView more_btn ;

        public TitleViewHolder(View view) {
            super(mContext,view);
            tvTitle = (TextView) view.findViewById(R.id.titleText);
            more_btn = (TextView) view.findViewById(R.id.more_btn);
        }
    }

    public class ItemViewHolder extends ViewHolder {
        RecyclerView rv ;

        public ItemViewHolder(View itemView) {
            super(mContext,itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.rv);
        }
    }
}
