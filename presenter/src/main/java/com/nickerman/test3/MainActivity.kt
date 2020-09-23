package com.nickerman.test3

import android.os.Bundle
import com.nickerman.test3.base.BaseActivity
import javax.inject.Inject

class MainActivity @Inject constructor(): BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}