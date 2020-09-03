package com.markfrain.formview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.markfrain.formview.R;
import com.markfrain.formview.utils.DpUtils;

/**
 * @authoer create by markfrain
 * @github https://github.com/furuiCQ
 * 时间: 2020/09/03
 * 描述: 附带底部文字描述的表单输入框,如需要做一个验证输入内容再给出提示的效果，请自行拓展
 */
public class FormEditTipsView extends FormEditView {
    private TextView tvTips;

    private String tipsText;

    private int tipsColor = Color.BLACK;

    private int tipsTextSize = 16;

    private int tipsTopMargin = 0;

    public FormEditTipsView(Context context) {
        super(context);
    }

    public FormEditTipsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormEditTipsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        tipsTextSize = DpUtils.sp2px(context, 16);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormEditTipsView);
            tipsText = typedArray.getString(R.styleable.FormEditTipsView_fetv_tips_text);
            tipsColor = typedArray.getColor(R.styleable.FormEditTipsView_fetv_tips_text_color, Color.BLACK);
            tipsTextSize = typedArray.getDimensionPixelSize(R.styleable.FormEditTipsView_fetv_tips_text_size, tipsTextSize);
            tipsTopMargin = typedArray.getDimensionPixelSize(R.styleable.FormEditTipsView_fetv_tips_top_margin, 0);
            typedArray.recycle();
        }
        initCustom();
    }

    @Override
    protected void initCustom() {
        super.initCustom();
        tvTips.setText(tipsText);
        tvTips.setTextSize(TypedValue.COMPLEX_UNIT_PX, tipsTextSize);
        tvTips.setTextColor(tipsColor);
        MarginLayoutParams layoutParams = (MarginLayoutParams) tvTips.getLayoutParams();
        layoutParams.topMargin = tipsTopMargin;
        tvTips.setLayoutParams(layoutParams);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        tvTips = rootView.findViewById(R.id.tv_tips);
    }

    @Override
    public int layoutId() {
        return R.layout.markfrain_form_edit_tips_view;
    }
}
