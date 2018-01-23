package com.wuchao.store.view.widget;

import android.content.Context;
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

public class MyEnterLayout extends LinearLayout {
    private TextView setItemTitle;
    private ImageView devider_line;
    private RelativeLayout item_layout;

    public MyEnterLayout(Context paramContext) {
        super(paramContext);
        a(paramContext);
    }

    public MyEnterLayout(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        a(paramContext);
    }

    private void a(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.settings_my_normal_item, this);
        item_layout = ((RelativeLayout) view.findViewById(R.id.item_layout));
        setPadding(DensityUtil.getDensity(context, 16), DensityUtil.getDensity(context, 0));
        setItemTitle = ((TextView) item_layout.findViewById(R.id.setItemTitle));
        devider_line = ((ImageView) view.findViewById(R.id.devider_line));
    }

    public void a() {
        devider_line.setVisibility(View.VISIBLE);
    }

    public void setPadding(int paramInt1, int paramInt2) {
        item_layout.setPadding(paramInt1, 0, paramInt2, 0);
    }


    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title))
            setItemTitle.setText(title);
    }
}