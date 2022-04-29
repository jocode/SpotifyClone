package com.crexative.spotifyclone.data.remote

import com.crexative.spotifyclone.data.models.playlist.PlaylistResponse
import retrofit2.http.GET

interface UserApi {

    @GET("/v1/me/playlists")
    suspend fun getUserPlaylists(
    ): PlaylistResponse
}