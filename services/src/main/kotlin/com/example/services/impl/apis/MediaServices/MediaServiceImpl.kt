package com.example.services.impl.apis.MediaServices

import com.example.data.services.MediaServiceAPI
import com.example.domain.dto.media_test.MediaResponce
import com.example.domain.dto.medial_library.MediaServiceResponse
import com.example.domain.dto.test.MainResponse
import javax.inject.Inject

class MediaServiceImpl @Inject constructor() : MediaServiceAPI {
    override suspend fun getMedia(): MediaResponce? {
        TODO("Not yet implemented")
    }
}