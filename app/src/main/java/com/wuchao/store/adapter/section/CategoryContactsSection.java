package com.wuchao.store.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuchao.store.R;
import com.wuchao.store.bean.CategoryBean;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

/**
 * @author: wuchao
 * @date: 2017/8/28 15:36
 * @desciption:
 */

public class CategoryContactsSection extends StatelessSection {

    private Context mContext;
    private List<CategoryBean.CategoryDataBean> categoryDataBeanList;
    private String title;

    public OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CategoryContactsSection(Context context, String title, List<CategoryBean.CategoryDataBean> categoryDataBeanList) {
        super(R.layout.applistitem_titlecard, R.layout.applistitem_subcat);
        this.mContext = context;
        this.title = title;
        this.categoryDataBeanList = categoryDataBeanList;
    }

    @Override
    public int getContentItemsTotal() {
        return categoryDataBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, final int position) {
        CategoryBean.CategoryDataBean categoryDataBean = categoryDataBeanList.get(position);
        holder.setImageUrl(R.id.appicon,categoryDataBean.getIconUrl());
        holder.setText(R.id.ItemTitle,categoryDataBean.getName());

        holder.setOnClickListener(R.id.rl_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        holder.setText(R.id.ItemTitle,title);
    }

    class HeaderViewHolder extends ViewHolder {

        TextView tvTitle;
        TextView tvMore;
        ImageView ivMore;

        public HeaderViewHolder(View view) {
            super(mContext, view);
            tvTitle = (TextView) view.findViewById(R.id.ItemTitle);
            tvMore = (TextView) view.findViewById(R.id.downbtn);
            ivMore = (ImageView) view.findViewById(R.id.arrow_right);

            tvMore.setVisibility(View.GONE);
            ivMore.setVisibility(View.GONE);

        }
    }

    class ItemViewHolder extends ViewHolder {

        ImageView appicon;
        TextView ItemTitle;

        public ItemViewHolder(View view) {
            super(mContext, view);
            appicon = (ImageView) view.findViewById(R.id.appicon);
            ItemTitle = (TextView) view.findViewById(R.id.ItemTitle);


        }
    }
}
