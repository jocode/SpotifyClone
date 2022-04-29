package com.crexative.spotifyclone.data

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.playlist.PlaylistResponse
import com.crexative.spotifyclone.data.remote.UserApi
import com.crexative.spotifyclone.domain.repositories.UserRepository
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getUserPlaylists(): Resource<PlaylistResponse> {
        return try {
            Resource.Success(userApi.getUserPlaylists())
        } catch (e: IOException) {
            Resource.Error(message = "${e.message}")
        } catch (e: Exception) {
            Resource.Error(message = "${e.message}")
        }
    }
}