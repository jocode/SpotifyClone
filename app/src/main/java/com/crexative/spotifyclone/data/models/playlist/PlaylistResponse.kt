package com.crexative.spotifyclone.data.models.playlist


import com.google.gson.annotations.SerializedName

data class PlaylistResponse(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)