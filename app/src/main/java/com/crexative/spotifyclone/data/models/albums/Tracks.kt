package com.crexative.spotifyclone.data.models.albums

data class Tracks(
    val href: String,
    val items: List<AlbumItem>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)