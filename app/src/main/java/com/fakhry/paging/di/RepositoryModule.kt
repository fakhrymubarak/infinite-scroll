package com.fakhry.paging.di

import com.fakhry.paging.data.MovieRepository
import com.fakhry.paging.data.MovieRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): MovieRepositoryContract
}