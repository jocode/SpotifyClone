package com.crexative.spotifyclone.data.remote

import com.crexative.spotifyclone.data.models.albums.AlbumResponse
import com.crexative.spotifyclone.data.models.albums.NewReleasesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {

    @GET("/v1/browse/new-releases")
    suspend fun getNewReleases(): NewReleasesResponse

    @GET("v1/albums/{id}")
    suspend fun getAlbum(
        @Path("id") albumId: String
    ): AlbumResponse
}