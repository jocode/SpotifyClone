package com.crexative.spotifyclone.data

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.tracks.TrackResponse
import com.crexative.spotifyclone.data.remote.TrackApi
import com.crexative.spotifyclone.domain.repositories.TrackRepository
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(private val api: TrackApi) :
    TrackRepository {
    override suspend fun getTrack(id: String): Resource<TrackResponse> {
        return try {
            Resource.Success(api.getTrack(id))
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}