package com.example.domain.dto.media_test

data class MediaResponse(
    val _type: String? = null,
    val categories: List<Category>? = null,
    val plan: Plan? = null,
    val playlists: List<Playlists>? = null,
    val programs: List<Program>? = null
)