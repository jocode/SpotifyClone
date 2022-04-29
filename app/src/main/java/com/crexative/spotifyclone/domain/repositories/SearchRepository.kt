package com.crexative.spotifyclone.domain.repositories

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.search.SearchResponse

interface SearchRepository {

    suspend fun getNewReleases(query: String): Resource<SearchResponse>
}