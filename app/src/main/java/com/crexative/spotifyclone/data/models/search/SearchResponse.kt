package com.crexative.spotifyclone.data.models.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val artists: Artists,
    val tracks: Tracks
)