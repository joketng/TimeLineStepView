# TimeLineStepView
时间轴和StepView，三种布局，支持水平，垂直和自定布局

## 添加依赖
```
implementation 'com.joketng:TimeLineStepView:1.0.1'
```
## 使用方法
- 在布局文件中添加TimeLineStepView
```
<com.joketng.timelinestepview.view.TimeLineStepView
        android:id="@+id/rvVertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:lineWidth="3dp"
        app:markSize="10dp"
        android:paddingStart="20dp"
        app:markStart="@drawable/shape_circle_orange"
        app:layoutType="right"
        />
```

- 在代码中调用
```
//OrientationShowType对应三种布局方式
//OrientationShowType.TIMELINE(时间轴方式)
//OrientationShowType.CENTER_VERTICAL(垂直方式)
//OrientationShowType.CENTER_HORIZONTAL(水平方式)
rvVertical.initData(listContent, OrientationShowType.CENTER_VERTICAL,
                object : TimeLineStepView.OnInitDataCallBack{
                    override fun onBindDataViewHolder(holder: TimeLineStepAdapter.CustomViewHolder, position: Int) {
                        
                    }

                    override fun createCustomView(leftLayout: ViewGroup, rightLayout: ViewGroup, holder: TimeLineStepAdapter.CustomViewHolder) {
                         //LayoutInflater.from(context).inflate(R.layout.item_add_left_view, leftLayout, true)
                         //LayoutInflater.from(context).inflate(R.layout.item_add_right_view, rightLayout, true)
                    }

                })
                .setLayoutType(type)//设置布局显示的样式左边：LayoutType.LEFT,右边：LayoutType.RIGHT,左右：LayoutType.ALL
                //设置stepview进度激活的mark图标
                .setMarkActive(ContextCompat.getDrawable(context,R.drawable.shape_dot_orange)!!)
                //设置stepview进度没激活的mark图标
                .setMarkInActive(ContextCompat.getDrawable(context,R.drawable.shape_dot_gray)!!)
                //设置stepview当前进度点的mark图标
                .setMarkCurrent(ContextCompat.getDrawable(context,R.drawable.shape_current)!!)
                //设置stepview第一个mark的图标
                .setMarkStart(ContextCompat.getDrawable(context,R.drawable.shape_circle_orange)!!)
                //设置stepview最后一个mark的图标
                .setMarkEnd(ContextCompat.getDrawable(context,R.drawable.shape_circle_orange)!!)
                //设置stepview线的宽度
                .setLineWidth(context.dipc(2))
                //设置stepview进度激活时线的颜色
                .setLineActiveColor(ContextCompat.getColor(context,R.color.c_main_orange))
                //设置stepview进度没有激活时线的颜色
                .setLineInActiveColor(ContextCompat.getColor(context,R.color.c_main_gray))
                //设置是否需要自定义布局(此时将createCustomView中的注释打开将自定义布局传入)
                .setIsCustom(true)
                
```

如果不喜欢在代码中设置控件属性的话可以选择布局文件中增加属性

```
   <com.joketng.timelinestepview.view.TimeLineStepView
          android:id="@+id/rvVertical"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:paddingStart="20dp"
          app:markSize="10dp"
          app:markStart="@drawable/shape_circle_orange"
          app:markEnd="@drawable/shape_circle_orange"
          app:markActive="@drawable/shape_dot_orange"
          app:markInActive="@drawable/shape_dot_gray"
          app:markCurrent="@drawable/shape_circle_orange"
          app:lineWidth="3dp"
          app:lineActiveColor="@color/c_main_orange"
          app:lineInActiveColor="@color/c_main_gray"
          app:isCustom="false"
          app:layoutType="right"
          />
```

如果需要在onBindDataViewHolder方法中更改控件的样式，在createCustomView方法中添加自定义布局

```
   rvVertical.initData(listContent, OrientationShowType.CENTER_VERTICAL,
                   object : TimeLineStepView.OnInitDataCallBack{
                       override fun onBindDataViewHolder(holder: TimeLineStepAdapter.CustomViewHolder, position: Int) {
                           holder.tvRightTitle.setTextColor(ContextCompat.getColor(context, R.color.c_main_black))
                           holder.tvLeftTitle.setTextColor(ContextCompat.getColor(context, R.color.c_main_black))
                           holder.tvRightTime.textSize = 12f
                           holder.tvLeftTime.textSize = 12f
                           holder.tvRightTime.setTextColor(ContextCompat.getColor(context, R.color.c_main_gray))
                           holder.tvLeftTime.setTextColor(ContextCompat.getColor(context, R.color.c_main_gray))
                       }
   
                       override fun createCustomView(leftLayout: ViewGroup, rightLayout: ViewGroup, holder: TimeLineStepAdapter.CustomViewHolder) {
                       }
   
                   }).setLayoutType(type)
```

## 截图
-timeLine样式加自定义布局
<div>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/custom_left.png" width=30% height=30%/>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/custom_right.png" width=30% height=30%/>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/custom_all.png" width=30% height=30%/>
</div>
-水平样式
<div>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/horizontal_top.png" width=30% height=30%/>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/horizontal_bottom.png" width=30% height=30%/>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/horizontal_all.png" width=30% height=30%/>
</div>
-垂直样式
<div>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/vertical_left.png" width=30% height=30%/>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/vertical_right.png" width=30% height=30%/>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/vertical_all.png" width=30% height=30%/>
</div>

-使用Maven
```
<dependency>
  <groupId>com.joketng</groupId>
  <artifactId>TimeLineStepView</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
# 联系方式
如果有什么问题，我没有及时回复的话，可以加我qq542490039，或者发邮件到joketng@163.com，我看到之后会回复的。



