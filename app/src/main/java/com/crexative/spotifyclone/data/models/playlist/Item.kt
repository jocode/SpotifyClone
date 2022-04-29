package com.crexative.spotifyclone.data.models.playlist


import com.google.gson.annotations.SerializedName

data class Item(
    val collaborative: Boolean,
    val description: String,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val owner: Owner,
    @SerializedName("primary_color")
    val primaryColor: Any,
    val `public`: Boolean,
    @SerializedName("snapshot_id")
    val snapshotId: String,
    val tracks: Tracks,
    val type: String,
    val uri: String
)