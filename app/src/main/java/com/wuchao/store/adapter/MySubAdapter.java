package com.wuchao.store.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuchao.store.R;
import com.wuchao.store.bean.MyGvBean;
import com.zhxu.recyclerview.App;

import java.util.List;

/**
 * Created by xzhang on 2017/5/25.
 */

public class MySubAdapter extends BaseAdapter {

    private Context mContext ;
    private List<MyGvBean> gvBeanList;

    public MySubAdapter(Context context, List<MyGvBean> gvBeanList) {
        mContext = context ;
        this.gvBeanList = gvBeanList ;
    }

    @Override
    public int getCount() {
        return gvBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return gvBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyGvBean gvBean = gvBeanList.get(position);

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

        holder.ItemTitle.setText(gvBean.getName());
        Glide.with(App.getContext()).load(gvBean.getIconId()).into(holder.appicon);


        return convertView;
    }

    class ViewHolder {
        ImageView appicon ;
        TextView ItemTitle ;

    }
}
