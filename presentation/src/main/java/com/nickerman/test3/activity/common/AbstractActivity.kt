package com.nickerman.test3.activity.common

import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity : AppCompatActivity() {
    lateinit var rootView: View
}