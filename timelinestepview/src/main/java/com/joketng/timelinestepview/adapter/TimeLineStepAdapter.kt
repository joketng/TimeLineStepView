package com.joketng.timelinestepview.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.joketng.timelinestepview.LayoutType
import com.joketng.timelinestepview.OrientationShowType
import com.joketng.timelinestepview.R
import com.joketng.timelinestepview.TimeLineState
import com.joketng.timelinestepview.bean.BaseBean
import com.joketng.timelinestepview.util.dipc
import com.joketng.timelinestepview.util.fid


/**
 * @Description:
 * @Author:  joketng
 * @Email:  joketng@163.com
 * @Time:  2018/11/1
 */
abstract class TimeLineStepAdapter<T: BaseBean>(val context: Context, val itemList: List<T>) :RecyclerView.Adapter<TimeLineStepAdapter.CustomViewHolder>(){
    private var lineInActive = ContextCompat.getColor(context, R.color.c_main_gray)
    private var lineActive = ContextCompat.getColor(context, R.color.c_main_orange)
    private var dotInActive = ContextCompat.getDrawable(context, R.drawable.shape_dot_gray)
    private var dotActive = ContextCompat.getDrawable(context, R.drawable.shape_dot_orange)
    private var dotDrawableCurrent = ContextCompat.getDrawable(context, R.drawable.shape_current)
    private var dotDrawableStart:Drawable? = null
    private var dotDrawableEnd:Drawable? = null
    private var lineWidth = context.dipc(2)
    private var dotMarkSize = context.dipc(15)
    private var leftLayoutBackground: Drawable? = null
    private var rightLayoutBackground: Drawable? = null
    private var isCustomView = false
    private var layoutType = LayoutType.RIGHT
    private var orientationType = OrientationShowType.TIMELINE

    override fun getItemCount(): Int  = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): CustomViewHolder {
        val itemView = when (orientationType) {
            OrientationShowType.TIMELINE -> {
                LayoutInflater.from(context).inflate(R.layout.item_time_line, parent, false)
            }
            OrientationShowType.CENTER_VERTICAL -> {
                LayoutInflater.from(context).inflate(R.layout.item_vertical_progress, parent, false)
            }
            OrientationShowType.CENTER_HORIZONTAL -> {
                LayoutInflater.from(context).inflate(R.layout.item_horizontal_progress, parent, false)
            }
        }
        val holder = CustomViewHolder(itemView!!, lineWidth, dotMarkSize)
        val leftCustomView = holder.leftLayout
        val rightCustomView = holder.rightLayout
        leftLayoutBackground?.let {
            leftCustomView.background = it
        }
        rightLayoutBackground?.let {
            rightCustomView.background = it
        }
        when (layoutType) {
            LayoutType.LEFT -> {
                leftCustomView.visibility = View.VISIBLE
                rightCustomView.visibility = View.GONE
            }
            LayoutType.RIGHT -> {
                leftCustomView.visibility = View.GONE
                rightCustomView.visibility = View.VISIBLE
            }
            LayoutType.ALL -> {
                leftCustomView.visibility = View.VISIBLE
                rightCustomView.visibility = View.VISIBLE
            }
        }
        if(isCustomView){
            holder.tvRightTitle.visibility = View.GONE
            holder.tvRightTime.visibility = View.GONE
            holder.tvLeftTitle.visibility = View.GONE
            holder.tvLeftTime.visibility = View.GONE
//            leftCustomView.removeAllViews()
//            rightCustomView.removeAllViews()
        }
        createCustomView(leftCustomView, rightCustomView, holder)
        return holder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        with(holder){
            when(position){
                0 -> {
                    imgLineStart.visibility = View.INVISIBLE
                    imgLineEnd.visibility = View.VISIBLE
                }
                itemList.size -1 ->{
                    imgLineEnd.visibility = View.INVISIBLE
                    imgLineStart.visibility = View.VISIBLE
                }
                else ->{
                    imgLineEnd.visibility = View.VISIBLE
                    imgLineStart.visibility = View.VISIBLE
                }
            }
            if(itemList.size == 1){
                imgLineEnd.visibility = View.INVISIBLE
                imgLineStart.visibility = View.INVISIBLE
            }
            when(itemList[position].timeLineState){
                TimeLineState.ACTIVE ->{
                    imgLineStart.setBackgroundColor(lineActive)
                    imgLineEnd.setBackgroundColor(lineActive)
                    imgMark.setImageDrawable(dotActive)
                }
                TimeLineState.INACTIVE ->{
                    imgLineStart.setBackgroundColor(lineInActive)
                    imgLineEnd.setBackgroundColor(lineInActive)
                    imgMark.setImageDrawable(dotInActive)
                }
                TimeLineState.CURRENT -> {
                    imgLineStart.setBackgroundColor(lineActive)
                    imgLineEnd.setBackgroundColor(lineActive)
                    imgMark.setImageDrawable(dotDrawableCurrent)
                }
            }

            if(position == 0){
                dotDrawableStart?.let {
                    imgMark.setImageDrawable(it)
                }
            }

            if(position == itemList.size -1){
                dotDrawableEnd?.let {
                    imgMark.setImageDrawable(it)
                }
            }
            tvRightTitle.text = itemList[position].rightTitle
            tvRightTime.text = itemList[position].rightTime
            tvLeftTitle.text = itemList[position].leftTitle
            tvLeftTime.text = itemList[position].leftTime
            if(TextUtils.isEmpty(itemList[position].rightTitle)){
                tvRightTitle.visibility = View.GONE
            }
            if(TextUtils.isEmpty(itemList[position].rightTime)){
                tvRightTime.visibility = View.GONE
            }
            if(TextUtils.isEmpty(itemList[position].leftTitle)){
                tvLeftTitle.visibility = View.GONE
            }
            if(TextUtils.isEmpty(itemList[position].leftTime)){
                tvLeftTime.visibility = View.GONE
            }
            onBindDataViewHolder(holder, position)
        }
    }

    abstract fun onBindDataViewHolder(holder: CustomViewHolder, position: Int)

    abstract fun createCustomView(leftCustomViewParent: ViewGroup, rightCustomViewParent: ViewGroup, holder: CustomViewHolder)

    fun setLineInActiveColor(@ColorInt color:Int){
        lineInActive = color
    }

    fun setLineActiveColor(@ColorInt color:Int){
        lineActive = color
    }

    fun setDrawableCurrent(@DrawableRes drawable: Drawable){
        dotDrawableCurrent = drawable
    }

    fun setDrawableActive(@DrawableRes drawable: Drawable){
        dotActive = drawable
    }

    fun setDrawableInActive(@DrawableRes drawable: Drawable){
        dotInActive = drawable
    }

    fun setDrawableStart(@DrawableRes drawable: Drawable){
        dotDrawableStart = drawable
    }

    fun setDrawableEnd(@DrawableRes drawable: Drawable){
        dotDrawableEnd = drawable
    }

    fun setLineWidth(width: Int){
        lineWidth = width
    }

    fun setMarkSize(markSize: Int){
        dotMarkSize = markSize
    }

    fun setRightLayoutBackground(@DrawableRes drawable: Drawable){
        rightLayoutBackground = drawable
    }

    fun setLeftLayoutBackground(@DrawableRes drawable: Drawable){
        leftLayoutBackground = drawable
    }

    fun setIsCustom(isCustom: Boolean = true){
        isCustomView = isCustom
    }

    fun setLayoutType(type: LayoutType){
        layoutType = type
    }

    fun setOrientationType(orientationShowType: OrientationShowType){
        orientationType = orientationShowType
    }


    class CustomViewHolder(itemView: View, lineWidth: Int, markSize: Int): RecyclerView.ViewHolder(itemView){
        val imgLineStart = itemView.fid<ImageView>(R.id.img_line_start)
        val imgLineEnd = itemView.fid<ImageView>(R.id.img_line_end)
        val imgMark = itemView.fid<ImageView>(R.id.img_dot)

        val tvRightTitle = itemView.fid<TextView>(R.id.tv_right_title)
        val tvRightTime = itemView.fid<TextView>(R.id.tv_right_time)
        val tvLeftTitle = itemView.fid<TextView>(R.id.tv_left_title)
        val tvLeftTime = itemView.fid<TextView>(R.id.tv_left_time)
        val llLine = itemView.fid<LinearLayout>(R.id.ll_line)
        val leftLayout = itemView.fid<LinearLayout>(R.id.ll_left)
        val rightLayout = itemView.fid<LinearLayout>(R.id.ll_right)
        val llRoot = itemView.fid<LinearLayout>(R.id.ll_root)

        init {
            imgLineStart.layoutParams.width = lineWidth
            imgLineEnd.layoutParams.width = lineWidth
            imgMark.layoutParams.width = markSize
            imgMark.layoutParams.height = markSize
        }
    }
}