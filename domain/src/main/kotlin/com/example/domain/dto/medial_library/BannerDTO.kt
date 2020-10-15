package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class BannerDTO(
    @field:SerializedName("url")
    var url: String? = null,
    @field:SerializedName("thumbnail")
    var thumbnail: String? = null,
    @field:SerializedName("resolutions")
    var resolutions: MutableList<ResolutionDTO?> = mutableListOf()
) : CommonDTO