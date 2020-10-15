package com.example.domain.interactor

import com.example.domain.Result
import com.example.domain.dto.test.MainResponse

interface MediaServiceUseCase {
    suspend fun getMedia(onError: suspend (Exception) -> Boolean = { true }): Result<MainResponse?>
}