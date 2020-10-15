package com.example.bl.common.mvp.login

import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.mvp.login.view.LoginView
import com.example.bl.common.navigation.MainScreen
import com.example.domain.interactor.MediaServiceUseCase
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class LoginPresenter @Inject constructor() : AbstractPresenter<LoginView>() {
    @Inject
    internal lateinit var useCase: MediaServiceUseCase

    fun navigateToMain() = launchUnit {
        viewState.showLoaderWithLock()
        val resp = useCase.getMedia()
        viewState.hideLoaderWithLock()
        router.navigateTo(MainScreen())
    }
}