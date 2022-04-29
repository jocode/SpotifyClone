package com.crexative.spotifyclone.domain.usecases

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.search.SearchResponse
import com.crexative.spotifyclone.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchSongsAndArtist @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(query: String): Resource<SearchResponse> =
        repository.getNewReleases(query)
}