package com.crexative.spotifyclone.data

import com.crexative.spotifyclone.core.Resource
import com.crexative.spotifyclone.data.models.search.SearchResponse
import com.crexative.spotifyclone.data.remote.SearchApi
import com.crexative.spotifyclone.domain.repositories.SearchRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {
    override suspend fun getNewReleases(query: String): Resource<SearchResponse> {

        return try {
            Resource.Success(searchApi.getNewReleases(query))
        } catch (e: IOException) {
            Resource.Error(message = "${e.message}")
        } catch (e: HttpException) {
            Resource.Error(message = "${e.message}")
        } catch (e: Exception) {
            Resource.Error(message = "${e.message}")
        }
    }
}