package com.example.bl.common.mvp.login

import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.mvp.login.view.LoginView
import com.example.bl.common.navigation.MainScreen
import com.example.domain.Result
import com.example.domain.Success
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class LoginPresenter @Inject constructor() : AbstractPresenter<LoginView>() {

    fun navigateToMain() {
        launchUnit {
            viewState.showLoaderWithLock()
            launchOnIO {
                loadContacts()
                launchUnit {
                    viewState.hideLoaderWithLock()
                    router.navigateTo(MainScreen())
                }
            }
        }
    }

    private fun loadContacts(): Result<Boolean> {
        val list = arrayOfNulls<Int>(100_000)
        list.forEachIndexed { index, i ->
            println("${i?.javaClass} hello")
            if (list.size - 1 == index) {
                return Success(true)
            }
        }
        return Success(false)
    }
}