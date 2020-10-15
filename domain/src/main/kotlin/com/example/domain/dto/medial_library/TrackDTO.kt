package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class TrackDTO(
    @field:SerializedName("key")
    var key: String?,
    @field:SerializedName("title")
    var title: String?,
    @field:SerializedName("order")
    var order: Int?,
    @field:SerializedName("duration")
    var duration: Int?,
    @field:SerializedName("media")
    var media: MediaDTO?,
    @field:SerializedName("isAvailable")
    var isAvailable: Boolean?
) : CommonDTO