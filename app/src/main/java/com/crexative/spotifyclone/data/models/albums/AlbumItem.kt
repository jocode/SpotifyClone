package com.crexative.spotifyclone.data.models.albums

import com.google.gson.annotations.SerializedName

data class AlbumItem(
    val artists: List<Artist>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("disc_number")
    val discNumber: Int,
    @SerializedName("duration_ms")
    val durationMs: Int,
    val explicit: Boolean,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    @SerializedName("is_local")
    val isLocal: Boolean,
    val name: String,
    @SerializedName("preview_url")
    val previewUrl: String,
    @SerializedName("track_number")
    val trackNumber: Int,
    val type: String,
    val uri: String,
    var image : String = ""
)