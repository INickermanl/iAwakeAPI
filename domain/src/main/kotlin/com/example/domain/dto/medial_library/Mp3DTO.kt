package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class Mp3DTO(
    @field:SerializedName("url")
    var url: String?,
    @field:SerializedName("headUrl")
    var headUrl: String?
) : CommonDTO