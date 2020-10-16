package com.example.domain.interactor

import com.example.domain.Result
import com.example.domain.dto.media_test.MediaResponse

interface MediaServiceUseCase {
    suspend fun getMedia(onError: suspend (Exception) -> Boolean = { true }): Result<MediaResponse>?
}