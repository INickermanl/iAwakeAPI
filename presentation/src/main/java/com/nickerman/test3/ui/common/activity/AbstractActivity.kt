package com.nickerman.test3.ui.common.activity

import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity : AppCompatActivity() {
    lateinit var rootView: View
}