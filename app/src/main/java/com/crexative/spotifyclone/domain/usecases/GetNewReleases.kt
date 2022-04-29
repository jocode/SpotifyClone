package com.crexative.spotifyclone.domain.usecases

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.albums.NewReleasesResponse
import com.crexative.spotifyclone.domain.repositories.AlbumRepository
import javax.inject.Inject

class GetNewReleases @Inject constructor(
    private val repository: AlbumRepository
) {

    suspend operator fun invoke(): Resource<NewReleasesResponse> = repository.getNewReleases()
}