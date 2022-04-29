package com.crexative.spotifyclone.domain.repositories

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.NewReleasesResponse

interface AlbumRepository {
    suspend fun getNewReleases(): Resource<NewReleasesResponse>
}