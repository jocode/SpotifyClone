package com.crexative.spotifyclone.domain.usecases

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.albums.AlbumResponse
import com.crexative.spotifyclone.domain.repositories.AlbumRepository
import javax.inject.Inject

class GetAlbum @Inject constructor(
    private val repository: AlbumRepository
) {

    suspend operator fun invoke(id: String): Resource<AlbumResponse> {
        return when (val response = repository.getAlbum(id)) {
            is Resource.Error -> {
                Resource.Error(response.message ?: "An unknown error happened")
            }
            is Resource.Success -> {

                response.data?.let {
                    val image = response.data.images.first().url

                    it.tracks.items.map { item ->
                        item.image = image
                    }
                }
                Resource.Success(response.data)
            }
        }
    }
}