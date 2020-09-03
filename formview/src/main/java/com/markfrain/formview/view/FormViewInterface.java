package com.markfrain.formview.view;

import android.view.View;

/**
 * @authoer create by markfrain
 * @github https://github.com/furuiCQ
 * 时间: 2020/09/03
 * 描述: 定义表单方法
 */
public interface FormViewInterface {
    int layoutId();

    /**
     * 根据每个FormView初始化视图
     *
     * @param rootView
     */
    void initView(View rootView);
}
