package com.crexative.spotifyclone.data.models.albums

data class Albums(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)