package com.example.data.services

import com.example.domain.dto.media_test.MediaResponce
import com.example.domain.dto.medial_library.MediaServiceResponse
import com.example.domain.dto.test.MainResponse
import retrofit2.http.GET

interface MediaServiceAPI {
     @GET("/api/v2/media-library/free")
     suspend fun getMedia(): MediaResponce?
}