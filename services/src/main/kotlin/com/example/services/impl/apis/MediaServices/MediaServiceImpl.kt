package com.example.services.impl.apis.MediaServices

import com.example.data.services.MediaServiceAPI
import com.example.domain.dto.media_test.MediaResponse
import javax.inject.Inject

class MediaServiceImpl @Inject constructor() : MediaServiceAPI {
    override suspend fun getMedia(): MediaResponse {
        TODO("Not yet implemented")
    }
}