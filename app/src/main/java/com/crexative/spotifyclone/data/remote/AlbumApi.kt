package com.crexative.spotifyclone.data.remote

import com.crexative.spotifyclone.data.models.albums.NewReleasesResponse
import retrofit2.http.GET

interface AlbumApi {

    @GET("/v1/browse/new-releases")
    suspend fun getNewReleases(): NewReleasesResponse
}