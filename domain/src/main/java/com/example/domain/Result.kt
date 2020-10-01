package com.example.domain

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toCollection

sealed class Result<Res>
class Failure<Res>(val e: Throwable) : Result<Res>()
class Success<Res>(val response: Res) : Result<Res>()

val <Res> Result<Res>.response: Res?
    get() = when (this) {
        is Success -> this.response
        else       -> null
    }
val <Res> Result<Res>.responseOrThrow: Res
    get() = when (this) {
        is Success -> this.response
        is Failure -> throw this.e
    }

suspend fun <Res> Result<Res>.onSuccess(onSuccess: suspend (Res) -> Unit): Result<Res> {
    if (this is Success) {
        onSuccess(response)
    }
    return this
}

suspend fun <Res> Result<Res>.onFailure(onFailure: suspend (Throwable) -> Unit): Result<Res> {
    if (this is Failure) {
        onFailure(e)
    }
    return this
}

fun <Res, T> Result<Res>.map(onSuccess: (Res) -> T): Result<T> {
    return when (this) {
        is Success -> Success(onSuccess(this.response))
        is Failure -> Failure(this.e)
    }
}


fun <Res> Result<Res?>.toFailureIfNull(): Result<Res> {
    return when (this) {
        is Success -> {
            val response = this.response
            if (response != null) Success(response)
            else Failure(NullPointerException())
        }
        is Failure -> Failure(this.e)
    }
}

suspend inline fun <T : Result<Res>, reified Res> kotlinx.coroutines.flow.Flow<T>.collectSuccess(crossinline action: suspend (value: Res) -> Unit) {
    this.collect {
        if (it is Success<*> && it.response is Res) action(it.response)
    }
}

suspend fun <R> kotlinx.coroutines.flow.Flow<R>.async(): Deferred<R> {
    val flow = this
    return coroutineScope { async { flow.last() } }
}

suspend fun <R> kotlinx.coroutines.flow.Flow<R>.last(): R {
    val latest: MutableList<R> = mutableListOf()
    toCollection(latest)
    return latest.last()
}