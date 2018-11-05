package com.joketng.timelinestepview.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup
import com.joketng.timelinestepview.LayoutType
import com.joketng.timelinestepview.OrientationShowType
import com.joketng.timelinestepview.R
import com.joketng.timelinestepview.adapter.TimeLineStepAdapter
import com.joketng.timelinestepview.bean.BaseBean
import com.joketng.timelinestepview.util.dipc

/**
 * @Description:
 * @Author:  joketng
 * @Email:  joketng@163.com
 * @Time:  2018/11/1
 */
class TimeLineStepView(context: Context, attributeSet: AttributeSet) : RecyclerView(context, attributeSet) {
    private var lineInActive = ContextCompat.getColor(context, R.color.c_main_gray)
    private var lineActive = ContextCompat.getColor(context, R.color.c_main_orange)
    private var dotInActive = ContextCompat.getDrawable(context, R.drawable.shape_dot_gray)
    private var dotActive = ContextCompat.getDrawable(context, R.drawable.shape_dot_orange)
    private var dotDrawableCurrent = ContextCompat.getDrawable(context, R.drawable.shape_current)
    private var dotDrawableStart: Drawable? = null
    private var dotDrawableEnd: Drawable? = null
    private var lineWidth = context.dipc(2)
    private var dotMarkSize = context.dipc(12)
    private var leftLayoutBackground: Drawable? = null
    private var rightLayoutBackground: Drawable? = null
    private var isCustomView = false
    private var layoutType = LayoutType.RIGHT
    private lateinit var timeLineStepAdapter: TimeLineStepAdapter<out BaseBean>
    init {
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.TimeLineStepView)
        lineWidth = ta.getDimensionPixelSize(R.styleable.TimeLineStepView_lineWidth, context.dipc(2))
        lineInActive = ta.getColor(R.styleable.TimeLineStepView_lineInActiveColor, ContextCompat.getColor(context, R.color.c_main_gray))
        lineActive = ta.getColor(R.styleable.TimeLineStepView_lineActiveColor, ContextCompat.getColor(context, R.color.c_main_orange))

        dotInActive = ta.getDrawable(R.styleable.TimeLineStepView_markInActive)
        dotActive = ta.getDrawable(R.styleable.TimeLineStepView_markActive)
        dotDrawableStart = ta.getDrawable(R.styleable.TimeLineStepView_markStart)
        dotDrawableEnd = ta.getDrawable(R.styleable.TimeLineStepView_markEnd)
        dotDrawableCurrent = ta.getDrawable(R.styleable.TimeLineStepView_markCurrent)
        dotMarkSize = ta.getDimensionPixelSize(R.styleable.TimeLineStepView_markSize, context.dipc(12))

        leftLayoutBackground = ta.getDrawable(R.styleable.TimeLineStepView_leftLayoutBackground)
        rightLayoutBackground = ta.getDrawable(R.styleable.TimeLineStepView_rightLayoutBackground)

        isCustomView = ta.getBoolean(R.styleable.TimeLineStepView_isCustom,false)
        layoutType = when(ta.getInt(R.styleable.TimeLineStepView_layoutType, 1)){
            0-> LayoutType.LEFT
            1-> LayoutType.RIGHT
            2-> LayoutType.ALL
            else -> {
                LayoutType.RIGHT
            }
        }

        ta.recycle()
    }

    fun <T : BaseBean>initData(list: List<T>, orientation: OrientationShowType, dataCallBack: OnInitDataCallBack): TimeLineStepView{
        when (orientation) {
            OrientationShowType.TIMELINE -> {
                layoutManager = LinearLayoutManager(context)
            }
            OrientationShowType.CENTER_VERTICAL -> {
                layoutManager = LinearLayoutManager(context)
            }
            OrientationShowType.CENTER_HORIZONTAL -> {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            }
        }

        timeLineStepAdapter = object : TimeLineStepAdapter<T>(context, list){
            override fun onBindDataViewHolder(holder: CustomViewHolder, position: Int) {
                dataCallBack.onBindDataViewHolder(holder, position)
            }

            override fun createCustomView(leftCustomViewParent: ViewGroup, rightCustomViewParent: ViewGroup, holder: CustomViewHolder) {
                dataCallBack.createCustomView(leftCustomViewParent, rightCustomViewParent, holder)
            }

        }
        setLineInActiveColor(lineInActive)
        setLineActiveColor(lineActive)
        dotActive?.let {
            setMarkActive(it)
        }
        dotInActive?.let {
            setMarkInActive(it)
        }
        dotDrawableCurrent?.let {
            setMarkCurrent(it)
        }
        dotDrawableStart?.let {
            setMarkStart(it)
        }
        dotDrawableEnd?.let {
            setMarkEnd(it)
        }
        setMarkSize(dotMarkSize)
        leftLayoutBackground?.let {
            setLeftLayoutBackground(it)
        }
        rightLayoutBackground?.let {
            setRightLayoutBackground(it)
        }
        setIsCustom(isCustomView)
        setLayoutType(layoutType)
        setOrientationType(orientation)

        adapter = timeLineStepAdapter
        return this
    }

    fun setLineInActiveColor(@ColorInt color:Int): TimeLineStepView{
        timeLineStepAdapter.setLineInActiveColor(color)
        return this
    }

    fun setLineActiveColor(@ColorInt color:Int): TimeLineStepView{
        timeLineStepAdapter.setLineActiveColor(color)
        return this
    }

    fun setMarkCurrent(@DrawableRes drawable: Drawable): TimeLineStepView{
        timeLineStepAdapter.setDrawableCurrent(drawable)
        return this
    }

    fun setMarkActive(@DrawableRes drawable: Drawable): TimeLineStepView{
        timeLineStepAdapter.setDrawableActive(drawable)
        return this
    }

    fun setMarkInActive(@DrawableRes drawable: Drawable): TimeLineStepView{
        timeLineStepAdapter.setDrawableInActive(drawable)
        return this
    }

    fun setMarkStart(@DrawableRes drawable: Drawable): TimeLineStepView{
        timeLineStepAdapter.setDrawableStart(drawable)
        return this
    }

    fun setMarkEnd(@DrawableRes drawable: Drawable): TimeLineStepView{
        timeLineStepAdapter.setDrawableEnd(drawable)
        return this
    }

    fun setLineWidth(width: Int): TimeLineStepView{
        timeLineStepAdapter.setLineWidth(width)
        return this
    }

    fun setMarkSize(markSize: Int): TimeLineStepView{
        timeLineStepAdapter.setMarkSize(markSize)
        return this
    }

    fun setRightLayoutBackground(@DrawableRes drawable: Drawable): TimeLineStepView{
        timeLineStepAdapter.setRightLayoutBackground(drawable)
        return this
    }

    fun setLeftLayoutBackground(@DrawableRes drawable: Drawable): TimeLineStepView{
        timeLineStepAdapter.setLeftLayoutBackground(drawable)
        return this
    }

    fun setIsCustom(isCustom: Boolean = true): TimeLineStepView{
        timeLineStepAdapter.setIsCustom(isCustom)
        return this
    }

    fun setLayoutType(type: LayoutType): TimeLineStepView{
        timeLineStepAdapter.setLayoutType(type)
        return this
    }

    private fun setOrientationType(orientation: OrientationShowType){
        timeLineStepAdapter.setOrientationType(orientation)
    }

    interface OnInitDataCallBack{
        fun onBindDataViewHolder(holder: TimeLineStepAdapter.CustomViewHolder, position: Int)
        fun createCustomView(leftLayout: ViewGroup, rightLayout: ViewGroup, holder: TimeLineStepAdapter.CustomViewHolder)
    }
}