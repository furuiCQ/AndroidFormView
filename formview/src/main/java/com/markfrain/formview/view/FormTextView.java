package com.markfrain.formview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.markfrain.formview.R;
import com.markfrain.formview.base.FormView;
import com.markfrain.formview.utils.DpUtils;

/**
 * @authoer create by markfrain
 * @github https://github.com/furuiCQ
 * 时间: 2020/09/15
 * 描述: 文字表单，无法输入编辑
 */
public class FormTextView extends FormView<String> {

    protected TextView etContent;

    private TextView tvUnit;
    //输入内容类型
    protected int maxLength;

    private String hintText;

    private int hintTextColor = Color.LTGRAY;

    private String ftvText;

    //内容宽度
    protected int textWidth = -2;

    private int textSize;

    private int textColor = Color.BLACK;

    private int textRightMargin = 0;

    private int textBg;

    private int textGravity = 1;

    private int textLines = 1;

    private int unitColor = Color.BLACK;

    private int unitTextSize = 16;

    private boolean unitVisible = false;

    private String unitText;

    private int unitRightMargin = 0;

    public FormTextView(Context context) {
        super(context);
    }

    public FormTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public int layoutId() {
        return R.layout.markfrain_form_text_view;
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        textSize = DpUtils.sp2px(context, 16);
        unitTextSize = DpUtils.sp2px(context, 16);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormTextView);

            hintText = typedArray.getString(R.styleable.FormTextView_ftv_hint);
            hintTextColor = typedArray.getColor(R.styleable.FormTextView_ftv_hint_color, Color.LTGRAY);
            textColor = typedArray.getColor(R.styleable.FormTextView_ftv_text_color, Color.BLACK);
            textSize = typedArray.getDimensionPixelSize(R.styleable.FormTextView_ftv_text_size, textSize);
            maxLength = typedArray.getInt(R.styleable.FormTextView_ftv_text_max_length, -1);

            textRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormTextView_ftv_text_right_margin, 0);
            ftvText = typedArray.getString(R.styleable.FormTextView_ftv_text);
            textWidth = typedArray.getInt(R.styleable.FormTextView_ftv_text_width, -2);
            textBg = typedArray.getResourceId(R.styleable.FormTextView_ftv_text_bg, -1);
            textGravity = typedArray.getInt(R.styleable.FormTextView_ftv_text_gravity, 0);
            textLines = typedArray.getInt(R.styleable.FormTextView_ftv_text_lines, 1);
            unitColor = typedArray.getColor(R.styleable.FormTextView_ftv_unit_color, Color.BLACK);
            unitTextSize = typedArray.getDimensionPixelSize(R.styleable.FormTextView_ftv_unit_text_size, unitTextSize);
            unitVisible = typedArray.getBoolean(R.styleable.FormTextView_ftv_unit_visible, false);
            unitText = typedArray.getString(R.styleable.FormTextView_ftv_unit_text);
            unitRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormTextView_ftv_unit_right_margin, 0);

            typedArray.recycle();
        }
        initContent();
        initTvUnit();
        initCustom();
    }

    @Override
    protected void initCustom() {

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        etContent = rootView.findViewById(R.id.et_content);
        tvUnit = rootView.findViewById(R.id.tv_unit);
    }

    protected void initContent() {
        setRightMargin(etContent, textRightMargin);
        setContentLayout();

        etContent.setHint(hintText);

        setText(ftvText);

        etContent.setHintTextColor(hintTextColor);
        etContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        etContent.setTextColor(textColor);
        if (maxLength > 0) {
            etContent.setMaxEms(maxLength);
            etContent.setEllipsize(TextUtils.TruncateAt.END);
        }
        etContent.setMaxLines(textLines);

        setTextGravity(textGravity);
    }

    protected void setContentLayout() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) etContent.getLayoutParams();
        layoutParams.width = textWidth;
        if (textWidth == 0) {
            layoutParams.weight = 1;
        } else {
            layoutParams.weight = 0;
        }
        etContent.setLayoutParams(layoutParams);
    }

    public void setTextGravity(int textGravity) {
        switch (textGravity) {
            default:
            case 1:
                etContent.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;
            case 0:
                etContent.setGravity(Gravity.TOP | Gravity.LEFT);
                break;
            case 2:
                etContent.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                break;
            case 3:
                etContent.setGravity(Gravity.BOTTOM);
                break;
            case 4:
                etContent.setGravity(Gravity.CENTER);
                break;
        }
    }


    protected void initTvUnit() {
        setRightMargin(tvUnit, unitRightMargin);
        tvUnit.setVisibility(unitVisible ? View.VISIBLE : View.GONE);
        tvUnit.setTextColor(unitColor);
        tvUnit.setTextSize(TypedValue.COMPLEX_UNIT_PX, unitTextSize);
        setUnitText(unitText);
    }

    public void setText(@StringRes int resid) {
        setText(getContext().getResources().getString(resid));
    }

    public void setFtvText(String ftvText) {
        this.ftvText = ftvText;
        setText(ftvText);
    }

    public void setText(String text) {
        this.ftvText = text;
        if (!TextUtils.isEmpty(text)) {
            etContent.setText(text);
        }
        if (textBg != -1 && !TextUtils.isEmpty(text)) {
            //防止给定的背景有内间距。没内容时还显示背景
            etContent.setBackgroundResource(textBg);
        } else {
            etContent.setBackground(null);
        }
    }

    public void setTextBg(Drawable drawable) {
        if (drawable != null && !TextUtils.isEmpty(ftvText)) {
            //防止给定的背景有内间距。没内容时还显示背景
            etContent.setBackground(drawable);
        } else {
            etContent.setBackground(null);
        }
    }

    public void setTextBg(int color) {
        if (color != -1 && !TextUtils.isEmpty(ftvText)) {
            //防止给定的背景有内间距。没内容时还显示背景
            etContent.setBackgroundColor(color);
        } else {
            etContent.setBackground(null);
        }
    }

    public void setTextColor(int resColor) {
        etContent.setTextColor(resColor);
    }

    public void setUnitText(String unitText) {
        tvUnit.setVisibility(View.VISIBLE);
        tvUnit.setText(unitText);
    }

    public TextView getContent() {
        return etContent;
    }

    public TextView gettvUnit() {
        return tvUnit;
    }

    @Override
    public String getValue() {
        if (etContent == null) {
            return null;
        }
        return etContent.getText().toString().trim();
    }

    public void setHint(String string) {
        hintText = string;
        etContent.setHint(hintText);
    }
}
