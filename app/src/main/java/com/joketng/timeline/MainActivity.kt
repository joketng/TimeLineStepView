package com.joketng.timeline

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @Description:
 * @Author:  joketng
 * @Time:  2018/10/30
 */
class MainActivity : Activity() {
    private lateinit var context:Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        btn_time_line.setOnClickListener {
            startActivity(Intent(context, TimeLineActivity::class.java))
        }
        btn_vertical_progress.setOnClickListener {
            startActivity(Intent(context,VerticalProgressActivity::class.java))
        }
        btn_horizontal_progress.setOnClickListener {
            startActivity(Intent(context, HorizontalProgressActivity::class.java))
        }
        btn_all_progress.setOnClickListener {
            startActivity(Intent(context, CustomProgressActivity::class.java))
        }
        btn_custom_progress.setOnClickListener {
            startActivity(Intent(context, AllProgressTestActivity::class.java))
        }
    }
}
