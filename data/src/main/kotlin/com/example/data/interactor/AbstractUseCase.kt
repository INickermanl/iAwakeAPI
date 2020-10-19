package com.example.data.interactor

import com.example.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractUseCase : UseCase {

    @Inject
    protected lateinit var errorHandler: ErrorHandler

    protected suspend fun <T> withCtxResult(
        onError: suspend (Exception) -> Boolean = { true },
        doThat: suspend () -> T
    ): Result<T> {
        return try {
            withContext(Dispatchers.IO) { Success(doThat()) }
        } catch (e: Exception) {
            if (onError(e)) errorHandler.handle(e)
            Failure(e)
        }
    }
}