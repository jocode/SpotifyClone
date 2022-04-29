package com.crexative.spotifyclone.data

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.albums.NewReleasesResponse
import com.crexative.spotifyclone.data.remote.AlbumApi
import com.crexative.spotifyclone.domain.repositories.AlbumRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumApi: AlbumApi
) : AlbumRepository {

    override suspend fun getNewReleases(): Resource<NewReleasesResponse> {
        return try {
            Resource.Success(albumApi.getNewReleases())
        } catch (e: IOException) {
            Resource.Error(message = "${e.message}")
        } catch (e: HttpException) {
            Resource.Error(message = e.message())
        } catch (e : Exception) {
            Resource.Error(message = "${e.message}")
        }
    }
}