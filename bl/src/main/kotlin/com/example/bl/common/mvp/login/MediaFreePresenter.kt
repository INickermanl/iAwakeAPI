package com.example.bl.common.mvp.login

import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.mvp.login.view.LoginView
import com.example.bl.common.navigation.MainScreen
import com.example.domain.interactor.MediaServiceUseCase
import com.example.domain.onSuccess
import com.example.domain.response
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class MediaFreePresenter @Inject constructor() : AbstractPresenter<LoginView>() {
    @Inject
    internal lateinit var useCase: MediaServiceUseCase

    override fun onFirstViewAttach() {
        getData(true)
    }

    private fun getData(needRefresh: Boolean) = launchUnit {
        viewState.showLoaderWithLock()
        useCase.getMedia()?.onSuccess {
            viewState.showList(it)
        }
        viewState.hideLoaderWithLock()
    }

    fun navigateToMain() = launchUnit {
        router.navigateTo(MainScreen())
    }
}