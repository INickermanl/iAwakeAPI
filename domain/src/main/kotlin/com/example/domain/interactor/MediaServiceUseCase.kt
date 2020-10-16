package com.example.domain.interactor

import com.example.domain.Result
import com.example.domain.dto.media_test.MediaResponce
import com.example.domain.dto.medial_library.MediaServiceResponse

interface MediaServiceUseCase {
    suspend fun getMedia(onError: suspend (Exception) -> Boolean = { true }): Result<MediaResponce?>
}