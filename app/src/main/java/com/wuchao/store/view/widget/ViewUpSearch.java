package com.wuchao.store.view.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wuchao.store.R;

/**
 * @author: wuchao
 * @date: 2017/8/29 12:06
 * @desciption:
 */

public class ViewUpSearch extends LinearLayout {

    private View search_icon;
    private View autoText;
    private float scale;
    private View search_circle;

    public ViewUpSearch(Context context) {
        super(context);
        initView(context);
    }

    public ViewUpSearch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ViewUpSearch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.search, this, true);
        search_icon = findViewById(R.id.search_icon);
        autoText = findViewById(R.id.autoText);
        search_circle = findViewById(R.id.search_circle);
    }

    public void updateShow(boolean isExpand) {
        double serchWid = autoText.getWidth() / 1.0;
        double wid = search_icon.getWidth() / 1.0;
        double fenshu = wid / serchWid;
        scale = (float) fenshu;

        if (isExpand) {
            expandSearch();
        } else {
            closeSearch();
        }
    }

    private void expandSearch() {
        search_circle.setVisibility(View.VISIBLE);
        search_icon.setVisibility(View.VISIBLE);
        //alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(search_circle, "alpha", 1f, 0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(autoText, "alpha", 0f, 1f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(autoText, "scaleX", scale, 1f);
        autoText.setPivotX(autoText.getWidth());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(anim1).with(anim2).with(anim3);
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    private void closeSearch() {
        search_circle.setVisibility(View.VISIBLE);
        search_icon.setVisibility(View.INVISIBLE);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(autoText, "scaleX", 1f, scale);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(search_circle, "alpha", 0f, 1f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(autoText, "alpha", 1f, 0f);
        autoText.setPivotX(autoText.getWidth());
        autoText.setPivotY(autoText.getHeight() / 2);
        AnimatorSet animSet1 = new AnimatorSet();
        animSet1.play(anim2).with(anim3).with(anim4);
        animSet1.setDuration(300);
        animSet1.start();
    }
}
