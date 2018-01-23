package com.wuchao.store.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuchao.store.R;
import com.wuchao.store.utils.DensityUtil;

/**
 * @author: wuchao
 * @date: 2017/9/1 11:00
 * @desciption:
 */

public class EnterLayout extends LinearLayout {

    private TextView setItemTitle;
    private TextView setItemContent;
    private ImageView devider_line;
    private RelativeLayout item_layout;

    public EnterLayout(Context context) {
        super(context);
        a(context);
    }

    public EnterLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        a(context);
    }

    public EnterLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        a(context);
    }

    private void a(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.settings_enter_normal_item, this);
        item_layout = ((RelativeLayout) view.findViewById(R.id.item_layout));
        setPadding(DensityUtil.getDensity(context, 16), DensityUtil.getDensity(context, 0));
        setItemTitle = ((TextView) view.findViewById(R.id.setItemTitle));
        setItemContent = ((TextView) view.findViewById(R.id.setItemContent));
        devider_line = ((ImageView) view.findViewById(R.id.devider_line));
    }

    private void a() {
        devider_line.setVisibility(View.VISIBLE);
    }

    private void setPadding(int density, int density1) {
        item_layout.setPadding(density, 0, density1, 0);
    }

    public void setMemo(String memo) {
        if (!TextUtils.isEmpty(memo)) ;
        setItemContent.setText(memo);

    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title))
            setItemTitle.setText(title);
    }
}
