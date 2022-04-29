package com.crexative.spotifyclone.domain.repositories

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.playlist.PlaylistResponse

interface UserRepository {

    suspend fun getUserPlaylists(): Resource<PlaylistResponse>
}