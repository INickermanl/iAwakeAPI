package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class Resolution2DTO(
    @field:SerializedName("url")
    var url: String?,
    @field:SerializedName("size")
    var size: Int?
) : CommonDTO