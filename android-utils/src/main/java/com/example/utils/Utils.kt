package com.example.utils

import android.content.Context
import android.util.TypedValue
import android.view.View
import java.util.*
import kotlin.concurrent.schedule

fun Context.dpToPx(dp: Int) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)
        .toInt()

fun Context.dpToPx(dp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.getClick(time: Long = 600L, todo: () -> Unit) {
    Timer("${this.id}", false).schedule(time) {
        todo.invoke()
    }
}

