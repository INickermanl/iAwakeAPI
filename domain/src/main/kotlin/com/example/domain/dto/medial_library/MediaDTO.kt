package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class MediaDTO(
    @field:SerializedName("mp3")
    var mp3: Mp3DTO?
) : CommonDTO