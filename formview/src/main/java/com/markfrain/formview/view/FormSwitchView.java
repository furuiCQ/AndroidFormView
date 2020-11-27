package com.markfrain.formview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.markfrain.formview.R;
import com.markfrain.formview.base.FormView;

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

    private String text;

    private int textSize;

    private int textColor = Color.BLACK;

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

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


    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        super.init(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormSwitchView);
            cbDrawable = typedArray.getDrawable(R.styleable.FormSwitchView_fsv_checkbox_drawable);
            cbRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormSwitchView_fsv_checkbox_right_margin, 0);
            text = typedArray.getString(R.styleable.FormSwitchView_fsv_text);
            textColor = typedArray.getColor(R.styleable.FormSwitchView_fsv_text_color, Color.BLACK);
            textSize = typedArray.getDimensionPixelSize(R.styleable.FormSwitchView_fsv_text_size, textSize);
            typedArray.recycle();
        }
        //  initCustom();

        cbState.setText(text);
        cbState.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        cbState.setTextColor(textColor);

        cbState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
                }
            }
        });
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        cbState = rootView.findViewById(R.id.cb_state);
    }

    @Override
    protected void initCustom() {
        cbState.setText(text);
        cbState.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        cbState.setTextColor(textColor);

//        cbState.setButtonDrawable(cbDrawable);
//        setRightMargin(cbState, cbRightMargin);

        cbState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
                }
            }
        });

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

    public void setChecked(boolean checked) {
        getCbState().setChecked(checked);
    }
}

