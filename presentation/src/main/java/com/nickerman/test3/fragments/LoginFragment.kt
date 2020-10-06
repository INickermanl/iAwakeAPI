package com.nickerman.test3.fragments

import android.os.Bundle
import android.view.View
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.base.fragment.AbstractFragment
import com.example.bl.common.mvp.login.LoginPresenter
import com.example.bl.common.mvp.login.view.LoginView
import com.example.utils.getClick
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class LoginFragment @Inject constructor() : AbstractFragment<LoginPresenter>(R.layout.fragment_login), LoginView {

    override val presenter: LoginPresenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AbstractApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginHelloText.getClick{
            presenter.navigateToMain()
        }
    }
}