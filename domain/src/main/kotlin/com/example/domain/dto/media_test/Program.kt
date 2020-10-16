package com.example.domain.dto.media_test

data class Program(
    val _type: String? = null,
    val banner: Banner? = null,
    val cover: CoverXX? = null,
    val descriptionHTML: String? = null,
    val headphones: Boolean? = null,
    val id: String? = null,
    val isAvailable: Boolean? = null,
    val isFeatured: Boolean? = null,
    val isFree: Boolean? = null,
    val title: String? = null,
    val tracks: List<Track>? = null
)