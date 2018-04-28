package com.wuchao.store.view.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuchao.store.R;

/**
 * @author: wuchao
 * @date: 2017/9/1 14:35
 * @desciption:
 */

public class FoldingTextView extends LinearLayout implements View.OnClickListener {

    private TextView titleTextView;
    private TextView contentTextView;
    private ImageView foldImage;
    private FrameLayout flContent;

    public FoldingTextView(Context context) {
        super(context);
        initView(context);
    }

    public FoldingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FoldingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        View view = View.inflate(context, R.layout.appdetail_item_desc, null);
        titleTextView = (TextView) view.findViewById(R.id.detail_desc_title_textview);
        contentTextView = (TextView) view.findViewById(R.id.detail_desc_content_textview);
        foldImage = (ImageView) view.findViewById(R.id.detail_desc_folding_imageview);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);

        flContent.getLayoutParams().height = getTitleHeight() + getShortHeight();
        contentTextView.setOnClickListener(this);
        foldImage.setOnClickListener(this);
        contentTextView.setTag(false);
        foldImage.setTag(false);
        addView(view);
    }

    public int getTitleHeight() {
        int measureWidth = titleTextView.getMeasuredWidth();
        TextView copyTextView = new TextView(getContext());
        copyTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.EXACTLY);
        copyTextView.setMaxLines(1);
        copyTextView.setLines(1);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
        copyTextView.measure(widthMeasureSpec, heightMeasureSpec);
        return copyTextView.getMeasuredHeight();
    }

    /**
     * 最小高度（收缩）
     *
     * @return
     */
    public int getShortHeight() {
        int measureWidth = contentTextView.getMeasuredWidth();
        TextView copyTextView = new TextView(getContext());
        copyTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.EXACTLY);
        copyTextView.setMaxLines(3);
        copyTextView.setLines(3);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
        copyTextView.measure(widthMeasureSpec, heightMeasureSpec);
        return copyTextView.getMeasuredHeight();
    }

    /**
     * 最大高度（展开后）
     *
     * @return
     */
    public int getTargetHeight() {
        int measureWidth = contentTextView.getMeasuredWidth();
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.EXACTLY);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(20000, MeasureSpec.AT_MOST);
        contentTextView.measure(widthMeasureSpec, heightMeasureSpec);
        return contentTextView.getMeasuredHeight();
    }

    public void setTitle(String title) {
        this.titleTextView.setText(title);
    }

    public void setContent(String content) {
        this.contentTextView.setText(content);
    }

    @Override
    public void onClick(View v) {
        expand();
    }

    private void expand() {
        contentTextView.setEnabled(false);
        foldImage.setEnabled(false);
        boolean contentFlag = (boolean) contentTextView.getTag();
        boolean foldFlag = (boolean) foldImage.getTag();
        int height;
        int targetHeight;
        if (!contentFlag && !foldFlag) {
            height = getShortHeight() + getTargetHeight();
            targetHeight = getTargetHeight();
            contentFlag = true;
            foldFlag = true;
        } else {
            height = getTargetHeight();
            targetHeight = getTitleHeight() + getShortHeight();
            contentFlag = false;
            foldFlag = false;
        }
        final ViewGroup.LayoutParams layoutParams = flContent.getLayoutParams();
        //值动画
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(height, targetHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) valueAnimator.getAnimatedValue();
                layoutParams.height = value;
                flContent.setLayoutParams(layoutParams);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                contentTextView.setEnabled(true);
                foldImage.setEnabled(true);
                boolean contentFlag = (boolean) contentTextView.getTag();
                boolean foldFlag = (boolean) foldImage.getTag();
                if (!contentFlag && !foldFlag) {
                    foldImage.setBackgroundResource(R.drawable.ic_public_arrow_down);
                } else {
                    foldImage.setBackgroundResource(R.drawable.ic_public_arrow_up);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        contentTextView.setTag(contentFlag);
        foldImage.setTag(foldFlag);
        valueAnimator.setDuration(300);
        valueAnimator.start();
    }
}
