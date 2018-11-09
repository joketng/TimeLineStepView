package com.joketng.timeline

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.joketng.timeline.bean.AllProgressBean
import com.joketng.timelinestepview.OrientationShowType
import com.joketng.timelinestepview.TimeLineState
import com.joketng.timelinestepview.adapter.TimeLineStepAdapter
import com.joketng.timelinestepview.util.dipc
import com.joketng.timelinestepview.view.TimeLineStepView
import kotlinx.android.synthetic.main.activity_all_progress_test.*
import kotlinx.android.synthetic.main.item_custom.view.*
import kotlinx.android.synthetic.main.item_header.*

class AllProgressTestActivity : AppCompatActivity() {
    private lateinit var context: Context
    private val listContent = mutableListOf<AllProgressBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_progress_test)
        tv_page_title.text = "自定义布局"
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
                val layoutParams = holder.imgMark.layoutParams as LinearLayout.LayoutParams
                layoutParams.width = context.dipc(80)
                layoutParams.height = context.dipc(80)
                holder.llLine.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
                layoutParams.setMargins(context.dipc(5),context.dipc(5),context.dipc(5),context.dipc(5))
                val drawable = RoundedBitmapDrawableFactory.create(resources,BitmapFactory.decodeResource(resources, R.mipmap.x2))
                drawable.isCircular = true
                holder.imgMark.setImageDrawable(drawable)
                holder.imgMark.setPadding(context.dipc(5),context.dipc(5),context.dipc(5),context.dipc(5))
                holder.imgMark.scaleType = ImageView.ScaleType.CENTER_CROP
                holder.imgMark.background = ContextCompat.getDrawable(context, R.drawable.shape_circle_orange)

                val drawable2 = RoundedBitmapDrawableFactory.create(resources,BitmapFactory.decodeResource(resources, R.mipmap.x2))
                drawable2.cornerRadius = context.dipc(6).toFloat()
                holder.itemView.img_one.setImageDrawable(drawable2)
                holder.itemView.img_two.setImageDrawable(drawable2)
                holder.itemView.img_three.setImageDrawable(drawable2)
                if(position % 3 == 0){
                    holder.itemView.img_two.visibility = View.VISIBLE
                } else {
                    holder.itemView.img_two.visibility = View.GONE
                }
            }

            override fun createCustomView(leftLayout: ViewGroup, rightLayout: ViewGroup, holder: TimeLineStepAdapter.CustomViewHolder) {
                LayoutInflater.from(context).inflate(R.layout.item_custom, rightLayout, true)

            }

        })
    }
}
