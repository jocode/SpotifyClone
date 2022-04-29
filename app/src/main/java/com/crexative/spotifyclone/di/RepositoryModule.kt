package com.crexative.spotifyclone.di

import com.crexative.spotifyclone.data.AlbumRepositoryImpl
import com.crexative.spotifyclone.data.SearchRepositoryImpl
import com.crexative.spotifyclone.domain.repositories.AlbumRepository
import com.crexative.spotifyclone.domain.repositories.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAlbumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository

    @Binds
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}