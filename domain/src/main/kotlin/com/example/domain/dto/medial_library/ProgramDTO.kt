package com.example.domain.dto.medial_library

import com.example.domain.dto.CommonDTO
import com.google.gson.annotations.SerializedName
import javax.sound.midi.Track


data class ProgramDTO(
    @field:SerializedName("id")
    var id: String?,
    @field:SerializedName("title")
    var title: String?,
    @field:SerializedName("isAvailable")
    var isAvailable: Boolean?,
    @field:SerializedName("isFree")
    var isFree: Boolean?,
    @field:SerializedName("isFeatured")
    var isFeatured: Boolean?,
    @field:SerializedName("banner")
    var banner: BannerDTO?,
    @field:SerializedName("cover")
    var cover: CoverDTO?,
    @field:SerializedName("headphones")
    var headphones: Boolean?,
    @field:SerializedName("descriptionHTML")
    var descriptionHTML: String?,
    @field:SerializedName("tracks")
    var tracks: List<Track?>?
) : CommonDTO