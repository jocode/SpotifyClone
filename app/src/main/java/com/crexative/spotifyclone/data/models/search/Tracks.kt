package com.crexative.spotifyclone.data.models.search


import com.google.gson.annotations.SerializedName

data class Tracks(
    val href: String,
    val items: List<ItemTrack>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)