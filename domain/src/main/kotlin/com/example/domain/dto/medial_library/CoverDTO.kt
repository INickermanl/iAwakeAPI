package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class CoverDTO(
    @field:SerializedName("url")
    var url: String?,
    @field:SerializedName("thumbnail")
    var thumbnail: String?,
    @field:SerializedName("resolutions")
    var resolutions: MutableList<Resolution2DTO?> = mutableListOf()
) : CommonDTO