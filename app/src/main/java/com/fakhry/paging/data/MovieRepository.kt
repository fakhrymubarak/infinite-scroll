package com.fakhry.paging.data

import androidx.paging.PagingData
import com.fakhry.paging.data.source.remote.RemoteMovieDataSource
import com.fakhry.paging.data.source.remote.network.ApiResponse
import com.fakhry.paging.data.source.remote.response.MovieData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource
    ) : MovieRepositoryContract {

    override fun getMoviePlayings(): Flow<Resource<List<MovieData>>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getMovPlayings().first()) {
                is ApiResponse.Success -> {
                    val data = apiResponse.data
                    emit(Resource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> emit(Resource.Success(listOf()))
            }
        }

    override fun getPagingMoviePlayings(): Flow<PagingData<MovieData>> = remoteDataSource.getPagingMoviePlayings()

}