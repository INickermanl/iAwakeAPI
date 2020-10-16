package com.nickerman.test3.fragments

import android.os.Bundle
import android.view.View
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.fragments.common.AbstractFragment
import com.example.bl.common.mvp.main.MainPresenter
import com.example.bl.common.mvp.main.view.MainView
import kotlinx.android.synthetic.main.fragment_main.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainFragment @Inject constructor() : AbstractFragment<MainPresenter>(R.layout.fragment_main), MainView {

    override val presenter: MainPresenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AbstractApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back.setOnClickListener {
            presenter.onBackPressed()
        }
    }
}