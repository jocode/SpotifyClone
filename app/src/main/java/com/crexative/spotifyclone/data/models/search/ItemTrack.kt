package com.crexative.spotifyclone.data.models.search

import com.google.gson.annotations.SerializedName

data class ItemTrack(
    val album: Album,
    val artists: List<ArtistTrack>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("disc_number")
    val discNumber: Int,
    @SerializedName("duration_ms")
    val durationMs: Int,
    val explicit: Boolean,
    @SerializedName("external_ids")
    val externalIds: ExternalIds,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    @SerializedName("is_local")
    val isLocal: Boolean,
    val name: String,
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: String,
    @SerializedName("track_number")
    val trackNumber: Int,
    val type: String,
    val uri: String
)