package com.fakhry.paging.data

import androidx.paging.PagingData
import com.fakhry.paging.data.source.remote.response.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryContract {
    fun getMoviePlayings(): Flow<Resource<List<MovieData>>>
    fun getPagingMoviePlayings(): Flow<PagingData<MovieData>>
}