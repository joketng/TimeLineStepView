# TimeLineStepView
Android中timeline布局支持时间轴的TimeLineStepView，三种布局，支持水平布局，垂直布局和自定义布局，截图如下
<div>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/vertical.gif" width=30% height=30%/>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/custom.gif" width=30% height=30%/>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/horizontal.gif" width=30% height=30%/>
</div>

## 添加依赖
```
implementation 'com.joketng:TimeLineStepView:1.0.1'
```
## 使用方法
- 在布局文件中添加TimeLineStepView
```xml
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
```kotlin
//OrientationShowType对应三种布局方式
//OrientationShowType.TIMELINE(时间轴方式)
//OrientationShowType.CENTER_VERTICAL(垂直方式)
//OrientationShowType.CENTER_HORIZONTAL(水平方式，支持左右滑动)
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
listContent的取值为 mutableListOf<BaseBean>()，当存在自定义布局的时候，listContent中添加的实体需要继承BaseBean这个实体，如果不需要自定义布局，可以直接添加实体BaseBean
```kotlin
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "08:30", rightTitle = "订单提交成功", rightTime = "订单提交成功描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "08:31", rightTitle = "订单付款成功", rightTime = "订单付款成功描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "10:00", rightTitle = "仓库已经接单", rightTime = "仓库已经接单描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "10:30", rightTitle = "仓库处理中", rightTime = "仓库处理中描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "11:00", rightTitle = "已出库", rightTime = "已出库描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "11:30", rightTitle = "已发货", rightTime = "已发货描述", timeLineState = TimeLineState.CURRENT))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "16:00", rightTitle = "已揽件", rightTime = "已揽件描述", timeLineState = TimeLineState.INACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "16:30", rightTitle = "运输中", rightTime = "运输中描述", timeLineState = TimeLineState.INACTIVE))
```
BaseBean的五个参数前四个为控件的文本,前四个参数不传的话该控件就不会显示,最后一个TimeLineState对应进度的三种状态TimeLineState.ACTIVE，TimeLineState.INACTIVE，TimeLineState.CURRENT，根据状态在onBindDataViewHolder方法中设置markdrawable，linecolor等,在设置markSize的时候，如果大小超过30dp,需要在createCustomView方法或者onBindDataViewHolder方法中调用holder.llLine.layoutParams.width设置为大于等于markSize的大小或者设置为WrapContent，如下
```kotlin
holder.llLine.layoutParams.width = context.dip(35)
holder.llLine.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
```
对于布局的显示位置有要求的话可以在createCustomView方法中通过layoutParams来控制
```kotlin
val rightLayoutParams = rightLayout.layoutParams as LinearLayout.LayoutParams
rightLayoutParams.rightMargin = context.dip(30)
```
如果不喜欢在代码中设置控件属性的话可以选择布局文件中增加属性
```xml
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

如果需要可以在onBindDataViewHolder方法中通过holder获取控件改变控件的样式，如果想要添加自定义的UI，可以在createCustomView方法中添加自己定义的布局文件，此时调用setIsCustom(true)即可

```kotlin
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
                            LayoutInflater.from(context).inflate(布局id, leftLayout, true)//添加左边自定义布局
                            LayoutInflater.from(context).inflate(布局id, rightLayout, true)//添加右边自定义布局

                       }
   
                   }).setLayoutType(type).setIsCustom(true)
```
自定义布局的一个截图如下
<div>
<image src="https://github.com/joketng/TimeLineStepView/blob/master/pic/custom2.png" width=40% height=40% />

**使用Maven**
```
<dependency>
  <groupId>com.joketng</groupId>
  <artifactId>TimeLineStepView</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
# 联系方式
如果有什么问题，我没有及时回复的话，可以加我qq542490039，或者发邮件到joketng@163.com，我看到之后会回复的。

# License
  Copyright 2018 joketng

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
