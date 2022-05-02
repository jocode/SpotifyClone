package com.crexative.spotifyclone.domain.repositories

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.albums.AlbumResponse
import com.crexative.spotifyclone.data.models.albums.NewReleasesResponse
import com.crexative.spotifyclone.data.models.tracks.TrackResponse

interface TrackRepository {
    suspend fun getTrack(id: String): Resource<TrackResponse>
}