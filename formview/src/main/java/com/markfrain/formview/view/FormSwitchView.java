package com.markfrain.formview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

import com.markfrain.formview.R;

/**
 * @authoer create by markfrain
 * @github https://github.com/furuiCQ
 * 高怀见物理 和气得天真
 * 时间: 2020-09-03
 * 描述: FromSwitchView
 */
public class FormSwitchView extends FormView<Boolean> {
    private CheckBox cbState;

    private Drawable cbDrawable;

    private int cbRightMargin;

    public FormSwitchView(Context context) {
        super(context);
    }

    public FormSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormSwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Boolean getValue() {
        return cbState.isChecked();
    }


    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormSwitchView);
            cbDrawable = typedArray.getDrawable(R.styleable.FormSwitchView_fsv_checkbox_drawable);
            cbRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormSwitchView_fsv_checkbox_right_margin, 0);
            typedArray.recycle();
        }
        initCustom();

    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        cbState = rootView.findViewById(R.id.cb_state);
    }

    @Override
    protected void initCustom() {
        cbState.setButtonDrawable(cbDrawable);
        setRightMargin(cbState, cbRightMargin);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setEnable(enabled, cbState);
    }

    public CheckBox getCbState() {
        return cbState;
    }

    @Override
    public int layoutId() {
        return R.layout.markfrain_form_switch_view;
    }
}
