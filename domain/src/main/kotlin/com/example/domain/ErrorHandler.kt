package com.example.domain

interface ErrorHandler {
    suspend fun handle(throwable: Throwable)
    suspend fun handle(throwable: Throwable,isNeedProcess: Boolean)
}