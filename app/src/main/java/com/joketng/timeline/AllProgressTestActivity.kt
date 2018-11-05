package com.joketng.timeline

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joketng.timelinestepview.adapter.TimeLineStepAdapter
import com.joketng.timeline.bean.AllProgressBean
import com.joketng.timelinestepview.util.dipc
import com.joketng.timelinestepview.view.TimeLineStepView
import com.joketng.timelinestepview.OrientationShowType
import com.joketng.timelinestepview.TimeLineState
import kotlinx.android.synthetic.main.activity_all_progress_test.*
import kotlinx.android.synthetic.main.item_add_left_view.view.*

class AllProgressTestActivity : AppCompatActivity() {
    private lateinit var context: Context
    private val listContent = mutableListOf<AllProgressBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_progress_test)

        repeat(7){
            val bean = AllProgressBean("item$it")
            bean.rightTitle = "所得到的多多多多多item$it"
            if(it == 3){
                bean.rightTitle = "说的就是看到就隆盛科技发我我积分为弗兰克健康检查绿茶女名称v女村民们处女吗没VM从VM你十多分十分时分我去问而我认为"
                bean.timeLineState = TimeLineState.CURRENT
            }
            if(it > 3){
                bean.timeLineState = TimeLineState.INACTIVE
            }
            listContent.add(bean)
        }
        context = this
        time_line.initData(listContent, OrientationShowType.CENTER_VERTICAL, object : TimeLineStepView.OnInitDataCallBack{
            override fun onBindDataViewHolder(holder: TimeLineStepAdapter.CustomViewHolder, position: Int) {
                if(position == 3){
                    holder.itemView.img_custom_left.visibility = View.VISIBLE
                    holder.itemView.img_custom_left.setImageResource(R.mipmap.x2)
                }else {
                    holder.itemView.img_custom_left.visibility = View.GONE
                }
                holder.itemView.tv_custom_left.text = listContent[position].rightTitle
                holder.llLine.layoutParams.width = dipc(30)
                if(position == 1){
                    holder.imgMark.layoutParams.width = dipc(30)
                    holder.imgMark.layoutParams.height = dipc(30)
                    holder.imgMark.setImageResource(R.drawable.shape_current)
                }

            }

            override fun createCustomView(leftCustomViewParent: ViewGroup, rightCustomViewParent: ViewGroup, holder: TimeLineStepAdapter.CustomViewHolder) {
                val view = LayoutInflater.from(context).inflate(R.layout.item_add_left_view, null, false)
//                val layoutParams = leftCustomViewParent.layoutParams as RelativeLayout.LayoutParams
//                layoutParams.bottomMargin = context.dipc(25)
//                layoutParams.topMargin = context.dipc(5)
                leftCustomViewParent.addView(view)
            }

        })

//        time_line.setIsCustom(true)
//                .setLineWidth(context.dipc(1))
//                .setMarkStart(ContextCompat.getDrawable(context, R.drawable.shape_circle_orange)!!)
//                .setMarkEnd(ContextCompat.getDrawable(context, R.drawable.shape_circle_orange)!!)
//                .setMarkSize(context.dipc(10))


    }
}
