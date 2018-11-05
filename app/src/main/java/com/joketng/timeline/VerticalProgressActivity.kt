package com.joketng.timeline

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.joketng.timelinestepview.adapter.TimeLineStepAdapter
import com.joketng.timelinestepview.bean.BaseBean
import com.joketng.timelinestepview.view.TimeLineStepView
import com.joketng.timelinestepview.LayoutType
import com.joketng.timelinestepview.OrientationShowType
import com.joketng.timelinestepview.TimeLineState
import kotlinx.android.synthetic.main.activity_vertical_progress.*


class VerticalProgressActivity : AppCompatActivity() {
    lateinit var context : Context
    val listContent = mutableListOf<BaseBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_progress)
        context = this
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "08:30", rightTitle = "订单提交成功", rightTime = "订单提交成功描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "08:31", rightTitle = "订单付款成功", rightTime = "订单付款成功描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "10:00", rightTitle = "仓库已经接单", rightTime = "仓库已经接单描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "10:30", rightTitle = "仓库处理中", rightTime = "仓库处理中描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "11:00", rightTitle = "已出库", rightTime = "已出库描述", timeLineState = TimeLineState.ACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "11:30", rightTitle = "已发货", rightTime = "已发货描述", timeLineState = TimeLineState.CURRENT))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "16:00", rightTitle = "已揽件", rightTime = "已揽件描述", timeLineState = TimeLineState.INACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "16:30", rightTitle = "运输中", rightTime = "运输中描述", timeLineState = TimeLineState.INACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "16:40", rightTitle = "配送中", rightTime = "配送中描述", timeLineState = TimeLineState.INACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "18:00", rightTitle = "签收", rightTime = "签收描述", timeLineState = TimeLineState.INACTIVE))
        listContent.add(BaseBean(leftTitle = "11-11", leftTime = "18:00", rightTitle = "结束", rightTime = "结束描述", timeLineState = TimeLineState.INACTIVE))

        rgGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbAll -> {
                    initView(LayoutType.ALL)
                }
                R.id.rbLeft -> {
                    initView(LayoutType.LEFT)
                }
                R.id.rbRight -> {
                    initView(LayoutType.RIGHT)
                }
                else -> {
                    initView(LayoutType.ALL)

                }
            }
        }
        rbAll.isChecked = true
    }

    private fun initView(type: LayoutType) {
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
    }
}
