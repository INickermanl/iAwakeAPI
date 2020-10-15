package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class ResolutionDTO(
    @field:SerializedName("url")
    var url: String?,
    @field:SerializedName("size")
    var size: SizeDTO?
) : CommonDTO