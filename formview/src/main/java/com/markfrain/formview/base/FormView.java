package com.markfrain.formview.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.markfrain.formview.R;
import com.markfrain.formview.utils.DpUtils;

import java.util.Map;

/**
 * @authoer create by markfrain
 * @github https://github.com/furuiCQ
 * 时间: 2020/09/03
 * 描述: 表单抽象类
 */
public abstract class FormView<T> extends FrameLayout implements FormViewInterface {

    protected FormClickListener clickListener;
    protected FormLongClickListener longClickListener;

    private Map map;

    //默认内间距
    private static final int DEFUALT_PADDING = 15;
    //左侧图标
    protected ImageView ivLeft;
    //表单标题左侧图标
    protected int leftImageResource;
    //默认为0，如果为0则按图标大小取值
    protected int leftImageMaxWidth;
    //默认为0，如果为0则按图标大小取值
    protected int leftImageMaxHeight;
    //左侧图标右边距
    protected int leftImageRightMargin;
    //左侧图标缩放类型
    protected ImageView.ScaleType ivLeftScaleType = ImageView.ScaleType.FIT_CENTER;

    //右侧图标
    protected ImageView ivRight;
    //表单标题右侧图标
    protected int rightImageResource;
    //默认为0，如果为0则按图标大小取值
    protected int rightImageMaxWidth;
    //默认为0，如果为0则按图标大小取值
    private int rightImageMaxHeight;
    //右侧图标右边距
    private int rightImageRightMargin;
    //右侧图标缩放类型
    protected ImageView.ScaleType ivRightScaleType = ImageView.ScaleType.FIT_CENTER;


    //图标缩放类型数组
    private static final ImageView.ScaleType[] sScaleTypeArray = {
            ImageView.ScaleType.MATRIX,
            ImageView.ScaleType.FIT_XY,
            ImageView.ScaleType.FIT_START,
            ImageView.ScaleType.FIT_CENTER,
            ImageView.ScaleType.FIT_END,
            ImageView.ScaleType.CENTER,
            ImageView.ScaleType.CENTER_CROP,
            ImageView.ScaleType.CENTER_INSIDE
    };
    //默认标题文字大小
    private static final int DEFUALT_TITLE_SIZE = 16;
    //标题
    protected TextView tvTitle;
    //标题文字
    protected String title;
    //标题宽度
    protected int titleWidth = -2;

    protected int titleMinWidth = 0;
    //标题文字样式
    protected int titleTextStyle = 2;
    //标题文字笔宽度
    private float textPaintWidth = 1.1F;
    //标题文字大小
    protected int titleTextSize;
    //标题文字颜色
    protected int titleColor = Color.BLACK;
    //标题文字右边距
    protected int titleRightMargin;
    //root布局
    protected ViewGroup llroot;

    public FormView(Context context) {
        super(context);
        init(context, null);
    }

    public FormView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FormView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setClickListener(FormClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(FormLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    protected void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        Log.i("init", "FormView");
        View rootView = LayoutInflater.from(context).inflate(layoutId(), this, false);
        addView(rootView);
        initView(rootView);
        initListener();
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormView);
            leftImageResource = typedArray.getResourceId(R.styleable.FormView_fv_left_image, 0);
            leftImageMaxWidth = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_left_image_max_width, 0);
            leftImageMaxHeight = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_left_image_max_height, 0);
            leftImageRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_left_image_right_margin, 0);
            final int leftIndex = typedArray.getInt(R.styleable.FormView_fv_left_image_scale_type, -1);
            if (leftIndex >= 0) {
                setIvLeftScaleType(sScaleTypeArray[leftIndex]);
            }
            rightImageResource = typedArray.getResourceId(R.styleable.FormView_fv_right_image, 0);
            rightImageMaxWidth = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_right_image_max_width, 0);
            rightImageMaxHeight = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_right_image_max_height, 0);
            rightImageRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_right_image_left_margin, 0);
            leftImageRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_left_image_right_margin, 0);
            final int rightIndex = typedArray.getInt(R.styleable.FormView_fv_right_image_scale_type, -1);
            if (rightIndex >= 0) {
                setIvRightScaleType(sScaleTypeArray[rightIndex]);
            }
            title = typedArray.getString(R.styleable.FormView_fv_title);
            titleWidth = typedArray.getInt(R.styleable.FormView_fv_title_width, -2);
            titleMinWidth = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_title_min_width, 0);
            titleColor = typedArray.getColor(R.styleable.FormView_fv_title_color, Color.BLACK);
            titleTextStyle = typedArray.getInt(R.styleable.FormView_fv_title_text_style, 2);
            titleTextSize = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_title_text_size, DpUtils.sp2px(context, DEFUALT_TITLE_SIZE));
            titleRightMargin = typedArray.getDimensionPixelSize(R.styleable.FormView_fv_title_right_margin, 0);
            textPaintWidth = typedArray.getFloat(R.styleable.FormView_fv_title_text_width, textPaintWidth);

            typedArray.recycle();
        }
        initIvLeft(context);
        initTitle();
        initIvRight(context);
    }

    @Override
    public void initView(View rootView) {
        Log.i("initView", "FormView");
        ivLeft = rootView.findViewById(R.id.iv_left);
        tvTitle = rootView.findViewById(R.id.tv_title);
        ivRight = rootView.findViewById(R.id.iv_right);
        llroot = rootView.findViewById(R.id.ll_root);
    }

    public void setIvLeftScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return;
        }
        if (ivLeftScaleType != scaleType) {
            ivLeftScaleType = scaleType;
        }
    }

    public void setIvRightScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return;
        }
        if (ivRightScaleType != scaleType) {
            ivRightScaleType = scaleType;
        }
    }

    protected void setRightMargin(View view, int margin) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
        layoutParams.rightMargin = margin;
        view.setLayoutParams(layoutParams);
    }

    private void initIvRight(Context context) {
        setRightMargin(ivRight, rightImageRightMargin);
        if (rightImageResource != 0) {
            ivRight.setImageDrawable(context.getResources().getDrawable(rightImageResource));
        }
        if (rightImageMaxWidth != 0) {
            ivRight.setMaxWidth(rightImageMaxWidth);
        }
        if (rightImageMaxHeight != 0) {
            ivRight.setMaxHeight(rightImageMaxHeight);
        }
        ivRight.setScaleType(ivRightScaleType);
    }

    public void setLeftImage(int drawableRes) {
        leftImageResource = drawableRes;
        ivLeft.setImageDrawable(getContext().getResources().getDrawable(leftImageResource));
    }

    public void setRightImage(int drawableRes) {
        rightImageResource = drawableRes;
        ivRight.setImageDrawable(getContext().getResources().getDrawable(rightImageResource));
    }
    public void setRightImage(Drawable drawableRes) {
        ivRight.setImageDrawable(drawableRes);
    }

    private void initIvLeft(Context context) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) ivLeft.getLayoutParams();
        layoutParams.rightMargin = leftImageRightMargin;
        ivLeft.setLayoutParams(layoutParams);
        if (leftImageResource != 0) {
            ivLeft.setImageDrawable(context.getResources().getDrawable(leftImageResource));
        }
        if (leftImageMaxWidth != 0) {
            ivLeft.setMaxWidth(leftImageMaxWidth);
        }
        if (leftImageMaxHeight != 0) {
            ivLeft.setMaxHeight(leftImageMaxHeight);
        }
        ivLeft.setScaleType(ivLeftScaleType);
    }

    //初始化标题
    private void initTitle() {
        if (tvTitle == null) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tvTitle.getLayoutParams();
        layoutParams.width = titleWidth;
        if (titleWidth == 0) {
            layoutParams.weight = 1;
        }
        tvTitle.setLayoutParams(layoutParams);
        if (titleMinWidth != 0) {
            tvTitle.setMinWidth(titleMinWidth);
        }
        if (textPaintWidth != 0.F) {
            TextPaint tp = tvTitle.getPaint();
            tp.setAntiAlias(true);
            tp.setStyle(Paint.Style.FILL_AND_STROKE);
            tp.setStrokeWidth(textPaintWidth);
        } else {
            switch (titleTextStyle) {
                case 0:
                    tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    break;
                case 1:
                    tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    break;
                default:
                    tvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    break;
            }
        }
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        tvTitle.setText(title);
        tvTitle.setTextColor(titleColor);
        setRightMargin(tvTitle, titleRightMargin);
    }

    public void setTitle(String res) {
        if (tvTitle != null) {
            title = res;
            tvTitle.setText(title);
        }
    }

    public void setTitle(@StringRes int res) {
        if (tvTitle != null) {
            title = getContext().getString(res);
            tvTitle.setText(title);
        }
    }

    protected void initListener() {
        //if (isEnabled()) {
        llroot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.click(v, map);
                }
            }
        });

        llroot.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.click(v, map);
                    return true;
                }
                return false;
            }
        });
        // }
    }


    /**
     * 设置点击后传递的map
     *
     * @param map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    protected void setEnable(boolean enable, View view) {
        //view.setEnabled(enable);
        view.setFocusable(enable);
    }

    /**
     * 获取表单值
     *
     * @return
     */
    public abstract T getValue();

    /**
     * 留存方法用于自定义效果，覆盖原有组件属性
     */
    protected abstract void initCustom();

    public void showRight(int visible) {
        if (ivRight != null) {
            ivRight.setVisibility(visible);
        }
    }

    public ImageView getIvLeft() {
        return ivLeft;
    }

    public TextView getTitle() {
        return tvTitle;
    }

    public ImageView getIvRight() {
        return ivRight;
    }
}

