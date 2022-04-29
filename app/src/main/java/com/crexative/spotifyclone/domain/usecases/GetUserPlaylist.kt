package com.crexative.spotifyclone.domain.usecases

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.playlist.PlaylistResponse
import com.crexative.spotifyclone.domain.repositories.UserRepository
import javax.inject.Inject

class GetUserPlaylist @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): Resource<PlaylistResponse> = repository.getUserPlaylists()
}