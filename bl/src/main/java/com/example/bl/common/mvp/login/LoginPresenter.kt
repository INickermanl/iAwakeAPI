package com.example.bl.common.mvp.login

import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.mvp.login.view.LoginView
import com.example.bl.common.navigation.MainScreen
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class LoginPresenter @Inject constructor() : AbstractPresenter<LoginView>() {

    fun navigateToMain() {
        router.navigateTo(MainScreen())
    }
}