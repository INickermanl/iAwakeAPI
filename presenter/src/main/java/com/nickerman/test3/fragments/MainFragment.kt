package com.nickerman.test3.fragments

import android.os.Bundle
import com.nickerman.test3.MyApplication
import com.nickerman.test3.R
import com.nickerman.test3.base.fragment.BaseFragment
import com.nickerman.test3.presenter.MainPresenter
import javax.inject.Inject

class MainFragment @Inject constructor(): BaseFragment<MainPresenter>(R.layout.fragment_main) {
    @Inject
    override lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}