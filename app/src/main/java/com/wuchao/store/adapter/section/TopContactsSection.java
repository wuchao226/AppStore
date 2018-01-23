package com.wuchao.store.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuchao.store.R;
import com.wuchao.store.bean.AppBean;
import com.wuchao.store.view.widget.DownloadProgressButton;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;


/**
 * Created by xzhang on 2017/5/24.
 */

public class TopContactsSection extends StatelessSection {

    private List<AppBean> appBeanList ;
    private Context mContext ;
    private String title ;

    public TopContactsSection(Context context, String title, List<AppBean> appBeanList) {
        super(R.layout.applistitem_titlecard,R.layout.applistitem_normal);
        this.appBeanList = appBeanList ;
        this.title = title ;
        this.mContext = context ;
    }

    @Override
    public int getContentItemsTotal() {
        return appBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(final ViewHolder holder, int position) {
        final AppBean appBean = appBeanList.get(position);
        holder.setText(R.id.appSerial,appBean.getAliasName());
        holder.setImageUrl(R.id.appicon,appBean.getIcon()) ;
        holder.setText(R.id.ItemTitle,appBean.getName()) ;
        holder.setText(R.id.ItemText_star,appBean.getSizeDesc()) ;
        holder.setText(R.id.memo,appBean.getMemo()) ;



        holder.setOnClickListener(R.id.AppListItem, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, AppDetailActivity.class) ;
//                intent.putExtra("packageName",appBean.getPackageName());
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new HeaderViewHolder(view) ;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        holder.setText(R.id.ItemTitle,title) ;
    }




    class HeaderViewHolder extends ViewHolder {

        TextView tvTitle;
        TextView tvMore ;
        ImageView ivMore ;

        public HeaderViewHolder(View view) {
            super(mContext,view);
            tvTitle = (TextView) view.findViewById(R.id.ItemTitle);
            tvMore = (TextView) view.findViewById(R.id.downbtn);
            ivMore = (ImageView) view.findViewById(R.id.arrow_right);

        }
    }

    class ItemViewHolder extends ViewHolder {

        TextView appSerial ;
        ImageView appicon ;
        DownloadProgressButton downbtn ;
        TextView ItemTitle ;
        TextView ItemText_star ;
        TextView memo ;

        public ItemViewHolder(View view) {
            super(mContext,view);
            appSerial = (TextView) view.findViewById(R.id.appSerial);
            appicon = (ImageView) view.findViewById(R.id.appicon);
            downbtn = (DownloadProgressButton) view.findViewById(R.id.downbtn);
            ItemTitle = (TextView) view.findViewById(R.id.ItemTitle);
            ItemText_star = (TextView) view.findViewById(R.id.ItemText_star);
            memo = (TextView) view.findViewById(R.id.memo);


        }
    }
}
