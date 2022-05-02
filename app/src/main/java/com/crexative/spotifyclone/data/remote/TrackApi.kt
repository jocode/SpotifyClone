package com.crexative.spotifyclone.data.remote

import com.crexative.spotifyclone.data.models.tracks.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TrackApi {

    @GET("v1/tracks/{id}")
    suspend fun getTrack(
        @Path("id") id: String
    ): TrackResponse
}