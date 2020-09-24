package com.nickerman.test3.fragments

import android.os.Bundle
import android.view.View
import com.nickerman.test3.MyApplication
import com.nickerman.test3.R
import com.nickerman.test3.base.fragment.BaseFragment
import com.nickerman.test3.presenter.LoginPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment @Inject constructor() : BaseFragment<LoginPresenter>(R.layout.fragment_login) {
    @Inject
    override lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginHelloText.setOnClickListener {
            presenter.navigateToMain()
        }
    }
}