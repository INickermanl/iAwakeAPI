package com.nickerman.test3

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.domain.ErrorHandler
import com.example.domain.core.exseptions.NotSupportedExseption
import com.example.utils.dialog.CustomDialog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    @Inject
    lateinit var uiContext: CoroutineDispatcher

    @Inject
    lateinit var ctxProvider: Provider<Context>

    override suspend fun handle(throwable: Throwable) {
        withContext(uiContext) {
            handle(throwable, true)
        }
    }

    override suspend fun handle(throwable: Throwable, isNeedProcess: Boolean) {
        Timber.i("Handle Error $throwable $isNeedProcess")
        if (!isNeedProcess) return
        when (throwable) {
            is NotSupportedExseption -> processUnknownException()
            else -> processUnknownException()
        }
    }

    private fun processUnknownException() {
        CustomDialog.Builder(AbstractApplication.instance.currentActivity)
            .setText(R.string.unknownError)
            .isAlert()
            .build().show()

    }

}