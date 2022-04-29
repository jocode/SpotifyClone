package com.crexative.spotifyclone.data.models.search


import com.google.gson.annotations.SerializedName

data class Artists(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)