package com.joketng.timelinestepview.util

import android.content.Context
import android.support.annotation.DimenRes
import android.support.annotation.IdRes
import android.view.View

/**
 * @Description:
 * @Author:  joketng
 * @Email:  joketng@163.com
 * @Time:  2018/11/1
 */
fun Context.dipc(value: Int): Int = (value * resources.displayMetrics.density).toInt()
fun Context.dipc(value: Float): Int = (value * resources.displayMetrics.density).toInt()


fun Context.spc(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()
fun Context.spc(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.px2dipc(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun Context.px2spc(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

fun Context.dimenc(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

inline fun <reified T : View> View.fid(@IdRes id: Int): T = findViewById<T>(id)