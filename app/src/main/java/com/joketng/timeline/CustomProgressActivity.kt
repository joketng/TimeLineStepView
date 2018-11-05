package com.joketng.timeline

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joketng.timelinestepview.adapter.TimeLineStepAdapter
import com.joketng.timeline.bean.CustomBean
import com.joketng.timelinestepview.view.TimeLineStepView
import com.joketng.timelinestepview.LayoutType
import com.joketng.timelinestepview.OrientationShowType
import kotlinx.android.synthetic.main.activity_custom_progress.*
import kotlinx.android.synthetic.main.item_add_left_view.view.*
import kotlinx.android.synthetic.main.item_add_right_view.view.*

class CustomProgressActivity : AppCompatActivity() {
    lateinit var context : Context
    val listContent = mutableListOf<CustomBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_progress)
        context = this
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())
        listContent.add(CustomBean())

        listContent.forEach {
            it.title = "海边吹着还风，漫无目的走在沙滩上。想我们在路上擦肩而过的时候，你可知道？我的眼睛不成器的流下了泪水。你依旧欢声笑语的走。"
        }

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
        rvVerticalCustom.initData(listContent, OrientationShowType.TIMELINE,
                object : TimeLineStepView.OnInitDataCallBack{
                    override fun onBindDataViewHolder(holder: TimeLineStepAdapter.CustomViewHolder, position: Int) {
                        when (type) {
                            LayoutType.LEFT -> {
                                if(position % 2 == 0){
                                    holder.itemView.img_custom_left.visibility = View.VISIBLE
                                    holder.itemView.img_custom_left.setImageResource(R.mipmap.x2)
                                } else {
                                    holder.itemView.img_custom_left.visibility = View.GONE
                                }
                                holder.itemView.tv_custom_left.text = listContent[position].title
                            }
                            LayoutType.RIGHT -> {
                                if(position % 2 == 1){
                                    holder.itemView.img_custom_right.visibility = View.VISIBLE
                                    holder.itemView.img_custom_right.setImageResource(R.mipmap.x2)
                                } else {
                                    holder.itemView.img_custom_right.visibility = View.GONE
                                }
                                holder.itemView.tv_custom_right.text = listContent[position].title
                            }
                            LayoutType.ALL -> {
                                if(position % 2 == 0){
                                    holder.itemView.img_custom_left.visibility = View.VISIBLE
                                    holder.itemView.img_custom_left.setImageResource(R.mipmap.x2)
                                } else {
                                    holder.itemView.img_custom_left.visibility = View.GONE
                                }
                                holder.itemView.tv_custom_left.text = listContent[position].title

                                if(position % 2 == 1){
                                    holder.itemView.img_custom_right.visibility = View.VISIBLE
                                    holder.itemView.img_custom_right.setImageResource(R.mipmap.x2)
                                } else {
                                    holder.itemView.img_custom_right.visibility = View.GONE
                                }
                                holder.itemView.tv_custom_right.text = listContent[position].title
                            }

                        }

                        holder.itemView.tv_custom_right.text = listContent[position].title
                    }

                    override fun createCustomView(leftLayout: ViewGroup, rightLayout: ViewGroup, holder: TimeLineStepAdapter.CustomViewHolder) {
                        LayoutInflater.from(context).inflate(R.layout.item_add_left_view, leftLayout, true)
                        LayoutInflater.from(context).inflate(R.layout.item_add_right_view, rightLayout, true)
                    }

                }).setLayoutType(type)
                .setIsCustom(true)
    }
}
