package com.example.data.services

import com.example.domain.dto.medial_library.MediaServiceResponse
import com.example.domain.dto.test.MainResponse
import retrofit2.http.GET

interface MediaService {
     @GET("/example")
     suspend fun getMedia(): MainResponse?
}