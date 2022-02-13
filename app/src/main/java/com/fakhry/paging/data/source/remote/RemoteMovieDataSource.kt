package com.fakhry.paging.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fakhry.paging.data.source.remote.paging.MoviePagingSource
import com.fakhry.paging.data.source.remote.network.ApiResponse
import com.fakhry.paging.data.source.remote.network.MovieApiService
import com.fakhry.paging.data.source.remote.response.MovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteMovieDataSource @Inject constructor(private val apiService: MovieApiService) {
    fun getMovPlayings(): Flow<ApiResponse<List<MovieData>>> =
        flow {
            try {
                val response = apiService.getMovPlayings()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


    fun getPagingMoviePlayings(): Flow<PagingData<MovieData>> =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(apiService) }
        ).flow


    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }
}