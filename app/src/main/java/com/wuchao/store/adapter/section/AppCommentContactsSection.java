package com.wuchao.store.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wuchao.store.R;
import com.wuchao.store.bean.AppCommentBean;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

/**
 * @author: wuchao
 * @date: 2017/9/26 17:37
 * @desciption:
 */

public class AppCommentContactsSection extends StatelessSection{
    private List<AppCommentBean.CommentsBean> commentsBeanList ;
    private String title ;
    private Context mContext ;

    public AppCommentContactsSection(Context context, String title, List<AppCommentBean.CommentsBean> commentsBeanList) {
        super(R.layout.appdetail_comment_list_title,R.layout.appdetail_comment_list_item);
        this.commentsBeanList = commentsBeanList ;
        this.mContext = context ;
        this.title = title ;
    }

    @Override
    public int getContentItemsTotal() {
        return commentsBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        AppCommentBean.CommentsBean commentsBean = commentsBeanList.get(position);
        holder.setText(R.id.detail_comment_user_textview,commentsBean.getAccountName());
        holder.setText(R.id.detail_comment_time_textview,commentsBean.getOperTime());
        holder.setText(R.id.detail_comment_user_client_textview,commentsBean.getPhone());
        holder.setText(R.id.detail_comment_content_textview,commentsBean.getCommentInfo());
        ((ItemViewHolder)holder).detail_comment_stars_ratingbar.setRating(Float.parseFloat(commentsBean.getStars()));
        holder.setText(R.id.detail_comment_version_textview,commentsBean.getVersionName());
        holder.setImageUrl(R.id.detail_comment_user_icon_imageview,commentsBean.getPhotoUrl());
        holder.setText(R.id.detail_comment_approve_counts_textview,commentsBean.getApproveCounts());
        holder.setText(R.id.detail_comment_reply_button_textview,commentsBean.getReplyCounts());
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        holder.setText(R.id.titleText,title) ;
    }

    public class TitleViewHolder extends ViewHolder{
        TextView tvTitle;

        public TitleViewHolder(View view) {
            super(mContext,view);
            tvTitle = (TextView) view.findViewById(R.id.titleText);
        }
    }

    class ItemViewHolder extends ViewHolder {

        ImageView detail_comment_user_icon_imageview ;
        TextView detail_comment_user_textview ;
        TextView detail_comment_time_textview ;
        TextView detail_comment_user_client_textview ;
        TextView detail_comment_content_textview ;
        RatingBar detail_comment_stars_ratingbar ;
        TextView detail_comment_version_textview ;
        TextView detail_comment_approve_counts_textview ;
        TextView detail_comment_reply_button_textview ;


        public ItemViewHolder(View view) {
            super(mContext,view);
            detail_comment_user_icon_imageview = (ImageView) view.findViewById(R.id.detail_comment_user_icon_imageview);
            detail_comment_user_textview = (TextView) view.findViewById(R.id.detail_comment_user_textview);
            detail_comment_time_textview = (TextView) view.findViewById(R.id.detail_comment_time_textview);
            detail_comment_user_client_textview = (TextView) view.findViewById(R.id.detail_comment_user_client_textview);
            detail_comment_content_textview = (TextView) view.findViewById(R.id.detail_comment_content_textview);
            detail_comment_stars_ratingbar = (RatingBar) view.findViewById(R.id.detail_comment_stars_ratingbar);
            detail_comment_version_textview = (TextView) view.findViewById(R.id.detail_comment_version_textview);
            detail_comment_approve_counts_textview = (TextView) view.findViewById(R.id.detail_comment_approve_counts_textview);
            detail_comment_reply_button_textview = (TextView) view.findViewById(R.id.detail_comment_reply_button_textview);
        }
    }
}
