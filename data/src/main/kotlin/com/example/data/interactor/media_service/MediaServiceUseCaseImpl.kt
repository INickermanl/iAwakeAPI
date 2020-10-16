package com.example.data.interactor.media_service

import com.example.data.interactor.AbstractUseCase
import com.example.data.services.MediaServiceAPI
import com.example.domain.interactor.MediaServiceUseCase
import retrofit2.Retrofit
import javax.inject.Inject

class MediaServiceUseCaseImpl @Inject constructor() : AbstractUseCase(), MediaServiceUseCase {
    @Inject
    internal lateinit var mediaServiceAPI: MediaServiceAPI

    override suspend fun getMedia(onError: suspend (Exception) -> Boolean) =
        withCtxResult(onError = onError) {
            return@withCtxResult mediaServiceAPI.getMedia()
        }
}