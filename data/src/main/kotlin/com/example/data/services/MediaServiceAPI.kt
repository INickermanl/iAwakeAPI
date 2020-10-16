package com.example.data.services

import com.example.domain.dto.media_test.MediaResponse
import retrofit2.http.GET

interface MediaServiceAPI {
     @GET("/api/v2/media-library/free")
     suspend fun getMedia(): MediaResponse
}