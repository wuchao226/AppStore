package com.wuchao.store.adapter.top;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuchao.store.R;
import com.wuchao.store.bean.TopBean;
import com.zhxu.recyclerview.App;

import java.util.List;


/**
 * Created by xzhang on 2017/5/13.
 */

public class TopSubAdapter extends BaseAdapter {

    private Context mContext ;
    private List<TopBean.TopTopBean> topBeanList;

    public TopSubAdapter(Context context, List<TopBean.TopTopBean> titleBeanList) {
        mContext = context ;
        this.topBeanList = titleBeanList ;
    }

    @Override
    public int getCount() {
        return topBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return topBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopBean.TopTopBean topBean = topBeanList.get(position);

        ViewHolder holder = null ;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.appdetail_subcat_title,null);
            holder = new ViewHolder() ;
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.appicon = (ImageView) convertView.findViewById(R.id.appicon);
        holder.ItemTitle = (TextView) convertView.findViewById(R.id.ItemTitle);

        holder.ItemTitle.setText(topBean.getName());
        Glide.with(App.getContext()).load(topBean.getIconUrl()).into(holder.appicon);

        return convertView;
    }

    class ViewHolder {
        ImageView appicon ;
        TextView ItemTitle ;

    }
}
