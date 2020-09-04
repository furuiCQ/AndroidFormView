package com.markfrain.formview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.markfrain.formview.R;

import java.util.Locale;

/**
 * @authoer create by markfrain
 * @github https://github.com/furuiCQ
 * 时间: 2020/09/03
 * 描述: 表单多行输入框底部的效果
 */
public class FormEditMultiView extends FormEditView {
    private LinearLayout llTitle;
    private TextView tvCount;

    private int layoutLeftPadding;
    private int layoutTopPadding;
    private int layoutRightPadding;
    private int layoutBottomPadding;

    private int contentLeftPadding;
    private int contentTopPadding;
    private int contentRightPadding;
    private int contentBottomPadding;

    private boolean countViewVisible;

    private int leftColor = Color.BLACK;
    private int rightColor = Color.BLACK;


    public FormEditMultiView(Context context) {
        super(context);
    }

    public FormEditMultiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormEditMultiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int layoutId() {
        return R.layout.markfrain_form_edit_multi_view;
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormEditMultiView);

            layoutLeftPadding = typedArray.getDimensionPixelSize(R.styleable.FormEditMultiView_femv_title_layout_left_padding, 0);
            layoutTopPadding = typedArray.getDimensionPixelSize(R.styleable.FormEditMultiView_femv_title_layout_top_padding, 0);
            layoutRightPadding = typedArray.getDimensionPixelSize(R.styleable.FormEditMultiView_femv_title_layout_right_padding, 0);
            layoutBottomPadding = typedArray.getDimensionPixelSize(R.styleable.FormEditMultiView_femv_title_layout_bottom_padding, 0);

            contentLeftPadding = typedArray.getDimensionPixelSize(R.styleable.FormEditMultiView_femv_content_left_padding, 0);
            contentTopPadding = typedArray.getDimensionPixelSize(R.styleable.FormEditMultiView_femv_content_top_padding, 0);
            contentRightPadding = typedArray.getDimensionPixelSize(R.styleable.FormEditMultiView_femv_content_right_padding, 0);
            contentBottomPadding = typedArray.getDimensionPixelSize(R.styleable.FormEditMultiView_femv_content_bottom_padding, 0);

            countViewVisible = typedArray.getBoolean(R.styleable.FormEditMultiView_femv_count_view_visible, false);
            leftColor = typedArray.getColor(R.styleable.FormEditMultiView_femv_count_left_text_color, Color.BLACK);
            rightColor = typedArray.getColor(R.styleable.FormEditMultiView_femv_count_right_text_color, Color.BLACK);

            typedArray.recycle();
        }
        initCustom();
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        llTitle = rootView.findViewById(R.id.ll_title);
        tvCount = rootView.findViewById(R.id.tv_count);
    }

    @Override
    protected void initCustom() {
        super.initCustom();
        llTitle.setPadding(layoutLeftPadding, layoutTopPadding, layoutRightPadding, layoutBottomPadding);
        getContent().setPadding(contentLeftPadding, contentTopPadding, contentRightPadding, contentBottomPadding);
        getContent().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setCountText(String.format(Locale.getDefault(), "%d", s.length()));
            }
        });

        tvCount.setVisibility(countViewVisible ? View.VISIBLE : View.GONE);
        if (countViewVisible) {
            setCountText("0");
        }
    }

    void setCountText(String left) {
        String text = String.format(Locale.getDefault(), "%s/%d", left, maxLength);
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan leftSpan = new ForegroundColorSpan(leftColor);
        spannableString.setSpan(leftSpan, 0, left.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan rightSpan = new ForegroundColorSpan(rightColor);
        spannableString.setSpan(rightSpan, left.length(), text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvCount.setText(spannableString);
    }


    @Override
    protected void initTvUnit() {
    }
}
