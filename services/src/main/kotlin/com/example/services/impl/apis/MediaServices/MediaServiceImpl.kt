package com.example.services.impl.apis.MediaServices

import com.example.data.services.MediaService
import com.example.domain.dto.medial_library.MediaServiceResponse
import com.example.domain.dto.test.MainResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class MediaServiceImpl @Inject constructor() : MediaService {
    override suspend fun getMedia(): MainResponse? {
        TODO("Not yet implemented")
    }
}