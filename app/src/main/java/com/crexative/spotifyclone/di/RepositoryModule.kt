package com.crexative.spotifyclone.di

import com.crexative.spotifyclone.data.AlbumRepositoryImpl
import com.crexative.spotifyclone.data.SearchRepositoryImpl
import com.crexative.spotifyclone.data.TrackRepositoryImpl
import com.crexative.spotifyclone.data.UserRepositoryImpl
import com.crexative.spotifyclone.domain.repositories.AlbumRepository
import com.crexative.spotifyclone.domain.repositories.SearchRepository
import com.crexative.spotifyclone.domain.repositories.TrackRepository
import com.crexative.spotifyclone.domain.repositories.UserRepository
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

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindTrackRepository(
        trackRepositoryImpl: TrackRepositoryImpl
    ): TrackRepository
}