package com.markfrain.formview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.markfrain.formview.R;

/**
 * @authoer create by markfrain
 * @github https://github.com/furuiCQ
 * 时间: 2020/09/03
 * 表单实现基类
 */
public class FormBaseView extends FormView<String> {

    public FormBaseView(Context context) {
        super(context);
    }

    public FormBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
    }

    @Override
    public int layoutId() {
        return R.layout.markfrain_form_base_view;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    protected void initCustom() {

    }
}
