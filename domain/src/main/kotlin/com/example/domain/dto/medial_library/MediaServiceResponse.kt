package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName

data class MediaServiceResponse(
    @field:SerializedName("programs")
    var programs: List<ProgramDTO?> = mutableListOf(),
    @field:SerializedName("categories")
    var categories: List<Any?> = mutableListOf(),
    @field:SerializedName("playlists")
    var playlists: List<Playlist?> = mutableListOf(),
    @field:SerializedName("type")
    var type: String? = null
) : CommonDTO