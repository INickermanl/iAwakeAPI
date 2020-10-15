package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class Playlist(
    @field:SerializedName("id")
    var id: String?,
    @field:SerializedName("order")
    var order: Int?,
    @field:SerializedName("title")
    var title: String?,
    @field:SerializedName("cover")
    var cover: CoverDTO?,
    @field:SerializedName("trackKeys")
    var trackKeys: List<String?>?
) : CommonDTO