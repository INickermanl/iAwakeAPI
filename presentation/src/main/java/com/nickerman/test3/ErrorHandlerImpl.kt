package com.nickerman.test3

import com.example.domain.ErrorHandler
import com.example.domain.core.exseptions.NotSupportedExseption
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    @Inject
    lateinit var uiContext: CoroutineDispatcher

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
        //TODO
//        CustomDialog.Builder(ctxProvider.get()).isAlert().setText(R.string.unknownError).build().show()
    }

}