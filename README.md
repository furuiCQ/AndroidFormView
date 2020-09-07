# AndroidFormView
Android form view use to forms

开发过程中遇到大量表单控件。故将原有项目中的表单抽离出来。
重构代码，精简结构。
抽离过程中遇到了一个小问题,后面将会讲到。
希望能在使用本项目的过程中，帮助你更快的搭建表单页面。
省去时间用于实现业务逻辑，以及写出更优美简洁的代码。
如果任何问题，请提交Issues,我将给予帮助。
如果给你启示，你也可以自己封装自己业务中的代码为组件库，方便统一管理。


如何引用

```gradle

implementation 'com.markfrain.utils:formview:1.0.0'

```


TODO 1.自定义分割线

# 表单组件场景

适用于工作流，业务流程页面的，报表页面的数据输入。
本项目抽离出最基础的表单，以及统一样式，多属性设置,
使得UI组件呈现保持一致。

## 代码结构

```java
--package
|-utils
|-view
	|-FormView //表单抽象基类
		|-FormEditView//输入框表单 其他变种输入框都基于此类实现
			|-FormEditMultiView //底部是输入框的输入框表单
			|-FormEditTipsView	//底部带提示语的输入框表单
		|-FormImageView  //图片表单
		|-FormRadioView  //单选框表单 
		|-FormSwitchView //switch表单
	|-FormViewInterface //表单接口方法 抽离出layoutId和initView
	|-FormClickListener //表单点击事件方法 FormLongClickListener继承与此接口
```

## FormView

将所有表单抽离出来后，可以看做4个控件。

1.左侧图标（例如:星号,各种图片）iv_left。
2.标题（例如:姓名/朋友圈/设置）tv_title。
3.表单内容(例如:输入框/图片/文字/单选框/switch等)content。
4.右侧图标(例如:右箭头/各种图标)iv_right。

子类都继承于FormView,故都具备其所有属性特征。
每个布局页面的父控件都是LinearLayout。除开Parent Layout的padding外，
所有的边距都是以右边距来进行设置。

FormView Attribute如下

|属性字段名称|属性描述|备注|
|:-|:-|:-|
|fv_left_image|左侧图标||
|fv_left_image_max_width|左侧图标最大宽度||
|fv_left_image_max_height|左侧图标最大高度||
|fv_left_image_right_margin|左侧图标的右边距||
|fv_left_image_scale_type|左侧图标的缩放类型|与ImageView的scaleType一致|
|fv_right_image|右侧图标||
|fv_right_image_max_width|右侧图标最大宽度||
|fv_right_image_max_height|右侧图标最大高度||
|fv_right_image_left_margin|右侧图标最大宽度||
|fv_right_image_scale_type|右侧图标的缩放类型|与ImageView的scaleType一致|
|fv_left_padding|表单左内间距||
|fv_top_padding|表单上内间距||
|fv_bottom_padding|表单下内间距||
|fv_right_padding|表单右内间距||
|fv_title|标题内容||
|fv_title_width|标题宽度模式|分为wrap_content和weight_content,wrap_content可以结合fv_title_min_width使用得到固定宽度效果|
|fv_title_min_width|标题宽度最小值||
|fv_title_text_size|标题文字大小||
|fv_title_text_style|标题文字Style|bold、italic、normal|
|fv_title_text_width|标题文字Panit的宽度|自定义宽度实现想要多粗就有多粗的效果|
|fv_title_right_margin|标题文字的右边距||
|fv_title_color|标题文字的颜色||


FormView 内部方法介绍

|方法名|用途|
|:-|:-|
|init(Context,AttributeSet)|初始化布局，以及获取属性|
|initView(View)|控件初始化findViewById|
|setMap(Map)|暴露外界传参方法,FormClickListener和FormLongClickListener将把该值返回|
|setEnable(Boolean)|禁用方法,方便业务详情页面根据权限动态使得控件是否能编辑/跳转等|
|T getValue()|获取控件的值，如输入框表单，将返回EditText的内容,如FormRadioView将返回选中状态|
|initCustom()|子类在重写init(Context,AttributeSet),拓展子类的表现效果|

## FormEditView 输入框表单和它的子类

表单内容content是一个EditText。
根据场景不一样，可以是单行,多行,在底部等。
输入框内容可能为金额,重量，身高等等。
故组件还有一个tvUnit的TextView用于显示单位。

FormEditView Attribute如下

|属性字段名称|属性描述|备注|
|:-|:-|:-|
|fev_hint|输入框提示语|
|fev_hint_color|输入框提示语颜色|
|fev_text_gravity|输入框文字内容定位|top bottom 适合多行显示配合fev_text_lines>1使用,left right 适合单行显示|
|fev_text_lines|输入框内容行数|
|fev_text_color|输入框内容颜色|
|fev_text_size|输入框文字大小|
|fev_text_max_length|输入框内容最大字数|
|fev_text_inputType|输入内容类型|text、phone、password、identifyCard(身份证号)、number、decimal|
|fev_text_right_margin|输入框右边距|
|fev_text_bg|输入框背景|
|fev_unit_text|单位文字|
|fev_unit_color|单位文字颜色|
|fev_unit_text_size|单位文字大小|
|fev_unit_visible|单位是否可见|
|fev_unit_right_margin|单位文字右边距|


### FormEditMultiView 底部多行输入框

此布局结构为上下结构。上面为左侧图标、标题、右侧图标。
下结构为相对布局，其中有输入框，字数统计TextView。

FormEditMultiView Attribute如下

|属性字段名称|属性描述|备注|
|:-|:-|:-|
|femv_title_layout_left_padding|标题布局左内间距|
|femv_title_layout_top_padding|标题布局上内间距|
|femv_title_layout_right_padding|标题布局右内间距|
|femv_title_layout_bottom_padding|标题布局下内间距|
|femv_content_left_padding|输入框左内间距|
|femv_content_top_padding|输入框上内间距|
|femv_content_right_padding|输入框右内间距|
|femv_content_bottom_padding|输入框下内间距|
|femv_count_view_visible|字数统计可见性|
|femv_count_left_text_color|字数统计/左侧文字颜色(不包含/)|
|femv_count_right_text_color|字数统计/右侧文字颜色(包含/)|

### FormEditTipsView 底部带提示输入框

此布局结构为上下结构。上面为左侧图标、标题、右侧图标。
下结构为TextView显示提示文字

FormEditTipsView Attribute如下

|属性字段名称|属性描述|备注|
|:-|:-|:-|
|fetv_tips_top_margin|提示文字上边距|
|fetv_tips_text|提示文字内容|
|fetv_tips_text_color|提示文字颜色|
|fetv_tips_text_size|提示文字大小|


## FormImageView 图片表单

属性只有一个fiv_src.跟ImageView的src一致。

## FormRadioView

FormRadioView Attribute如下


|属性字段名称|属性描述|备注|
|:-|:-|:-|
|frv_layout_min_width|RadioGroup的最小宽度。默认是wrap_content|
|frv_text_gravity|RadioGroup内容对齐方式left/right。都是垂直居中的|
|frv_text_drawable_padding|单选按钮的drawabPadding属性|
|frv_text_drawable_left|单选左侧按钮图标|
|frv_text_drawable_top|单选上侧按钮图标|
|frv_text_drawable_right|单选右侧按钮图标|
|frv_text_drawable_bottom|单选下侧按钮图标|
|frv_left_to_right_margin|左边单选按钮的右边距|
|frv_text_color|单选按钮文字颜色|
|frv_text_size|单选按钮文字大小|
|frv_left_text|左侧单选按钮文字内容|
|frv_right_text|右侧单选按钮文字内容|
	
## FormSwitchView

FormSwitchView Attribute如下

|属性字段名称|属性描述|备注|
|:-|:-|:-|
|fsv_checkbox_drawable|CheckBox的drawableLeft|
|fsv_checkbox_right_margin|CheckBox的右边距|


## 如果使用

根据上面的属性解析,以及查看源码。
每处代码都有注释,希望能帮到你。

```xml

	<com.markfrain.formview.view.FormSwitchView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingLeft="15dp"
            android:paddingTop="18dp"
            android:paddingRight="15dp"
            android:paddingBottom="18dp"
            app:fsv_checkbox_drawable="@drawable/checkbox_switch"
            app:fsv_checkbox_right_margin="10dp"
            app:fv_left_image="@drawable/ic_svg_star"
            app:fv_right_image="@drawable/ic_svg_right_arrow"
            app:fv_title="测试文本标题"
            app:fv_title_right_margin="15dp"
            app:fv_title_width="weight_content" />

        <View
            android:layout_width="match_parent"
            android:layout_height="1px"
            android:background="@android:color/darker_gray" />

        <com.markfrain.formview.view.FormRadioView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingLeft="15dp"
            android:paddingTop="18dp"
            android:paddingRight="15dp"
            android:paddingBottom="18dp"
            app:frv_layout_min_width="150dp"
            app:frv_left_to_right_margin="10dp"
            app:frv_text_color="@color/radio_text_color"
            app:frv_text_drawable_left="@drawable/radio_check"
            app:frv_text_drawable_padding="10dp"
            app:fv_left_image="@drawable/ic_svg_star"
            app:fv_right_image="@drawable/ic_svg_right_arrow"
            app:fv_title="测试文本标题"
            app:fv_title_right_margin="15dp"
            app:fv_title_width="weight_content" />

        <View
            android:layout_width="match_parent"
            android:layout_height="1px"
            android:background="@android:color/darker_gray" />

        <com.markfrain.formview.view.FormImageView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingLeft="15dp"
            android:paddingTop="18dp"
            android:paddingRight="15dp"
            android:paddingBottom="18dp"
            app:fiv_src="@mipmap/base_iv_header_member"
            app:fv_left_image="@drawable/ic_svg_star"
            app:fv_right_image="@drawable/ic_svg_right_arrow"
            app:fv_title="测试文本标题"
            app:fv_title_right_margin="15dp" />

        <View
            android:layout_width="match_parent"
            android:layout_height="1px"
            android:background="@android:color/darker_gray" />

        <com.markfrain.formview.view.FormEditView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingLeft="15dp"
            android:paddingTop="18dp"
            android:paddingRight="15dp"
            android:paddingBottom="18dp"
            app:fev_hint="测试提示文字"
            app:fev_text_bg="@null"
            app:fev_text_gravity="top"
            app:fev_unit_text="单位"
            app:fv_left_image="@drawable/ic_svg_star"
            app:fv_right_image="@drawable/ic_svg_right_arrow"
            app:fv_title="测试文本标题"
            app:fv_title_right_margin="15dp" />

```


## 关于RadioButton共用Drawable导致。切换展现效果出现问题的BUG。

当前FormRadioView 有如下4个属性。

```xml
 	<attr name="frv_text_drawable_left" format="reference" />
        <attr name="frv_text_drawable_top" format="reference" />
        <attr name="frv_text_drawable_right" format="reference" />
        <attr name="frv_text_drawable_bottom" format="reference" />
        <attr name="frv_left_to_right_margin" format="dimension" />
```

早之前我的代码如下编写，即获取到Drawable后，两个RadioButton同时设置Drawable.

```java
     
    rbLeft.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
	rbRight.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, topDrawable, rightDrawable, bottomDrawable);

```

咋一看并没有什么不对。感兴趣的朋友，你可以自己实现试试。

我这边直接说结果:

1.设置同一个Drawable导致两个RadioButton都持有Drawable的引用

2.在切换Drawable的时候，由于共用引用。进而RadioButton的展示的Drawable的效果和其mChecked不一致。

为什么呢？

1.首先Debug定位.由于设置的Drawable是一个selector的,
TypedArray.getDrawable之后获取到的是StateListDrawable。

2.其次RadioButton向上找父类。阅读TextView源码
关键代码如下:

```java
	public TextView(
            Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ...
        //1.将TypedArray.getDrawable的drawableLeft, drawableTop, drawableRight, drawableBottom。设置给TextView
        setCompoundDrawablesWithIntrinsicBounds(
                drawableLeft, drawableTop, drawableRight, drawableBottom);
    }
    //2. setCompoundDrawablesWithIntrinsicBounds最后执行到这个方法
     public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top,
            @Nullable Drawable right, @Nullable Drawable bottom) {
     	...
		if (left != null) {
	         ...
	        left.setCallback(this);
	        //3.这里有个setCallBack 看名字都知道是个回调函数
	        //View源码 中 public class View implements Drawable.Callback。
	        ...
	    }
	}

	//4.再看看StateListDrawable这个类
	//既然状态混乱了，肯定是state相关的方法出现了问题
	@Override
    protected boolean onStateChange(int[] stateSet) {
        //通过状态获取到状态数组下标
        int idx = mStateListState.indexOfStateSet(stateSet);
        ...
        //省去上面代码，看关键的这句selectDrawable(idx);

        return selectDrawable(idx) || changed;
    }
    //5.通过selectDrawable跟踪到DrawableContainer类 
    public boolean selectDrawable(int index) {
    	...
    	invalidateSelf();//看名字叫重绘自己
    }
    //6.跟踪到Drawable类中
    public void invalidateSelf() {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }
    //7. TextView 的 invalidateDrawable覆盖了View的该方法
    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {

    }
    //8. CompoundButton的源码如下:
     @Override
    public void setChecked(boolean checked) {
    	...
    	refreshDrawableState();
    	...
    }
    public void refreshDrawableState() {
        ...
        drawableStateChanged();
        ...
	}
    //9. CompoundButton中覆写了drawableStateChanged
	@Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        final Drawable buttonDrawable = mButtonDrawable;
        if (buttonDrawable != null && buttonDrawable.isStateful()
                && buttonDrawable.setState(getDrawableState())) {
            invalidateDrawable(buttonDrawable);
        }
    }
    //10.buttonDrawable.setState(getDrawableState())跟踪到Drawable类
    //这里又回到了第4点
    public boolean setState(@NonNull final int[] stateSet) {
        if (!Arrays.equals(mStateSet, stateSet)) {
            mStateSet = stateSet;
            return onStateChange(stateSet);
        }
        return false;
    }

```

由此可以看出Drawable和CompoundButton通过CallBack关联。2者互相影响。


```java
	//这里设置了StateListDrawable的CallBack为rbLeft
	rbLeft.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
	//这里再次把 StateListDrawable的CallBack为rbRight
	rbRight.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
	//由于上面源码的分析。
	//rbLeft 持有Drawable引用
	//rbRight 持有Drawable引用。而Drawable的CallBack设置为rbRight。

	//则就会导致乱序,出现以下怪异情况
	//设置RadioGroup默认选中rbLeft。
	//1.点击右侧按钮。选中状态如下:  
	// I/rbRight: true
	// I/rbLeft: false
	// 右侧选中按钮效果，左侧未选中按钮效果。
	//2.点击左侧按钮 选中状态如下:  
	// I/rbLeft: true
	// I/rbRight: false
	//两个RadioButton都选中了。
	//为啥。因为rbLeft改变了StateListDrawable的状态。
	//而StateListDrawable的CallBack绑定的rbRight。
	//所以rbRight的显示改变了，但状态仍然为false。
```



