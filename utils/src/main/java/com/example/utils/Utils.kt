package com.example.utils

import android.content.Context
import android.util.TypedValue

fun Context.dpToPx(dp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()

fun Context.dpToPx(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)