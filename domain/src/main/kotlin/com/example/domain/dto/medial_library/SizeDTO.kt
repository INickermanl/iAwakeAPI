package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class SizeDTO(
    @field:SerializedName("width")
    var width: Int?,
    @field:SerializedName("height")
    var height: Int?
) : CommonDTO