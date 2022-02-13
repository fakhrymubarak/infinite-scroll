package com.fakhry.paging.data.source.remote.network

import com.fakhry.paging.BuildConfig
import com.fakhry.paging.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    /* Get Now Playing Movies  */
    @GET("movie/now_playing?")
    suspend fun getMovPlayings(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1
    ): MovieResponse


    companion object {
        private const val API_KEY = BuildConfig.TMDB_API_KEY
    }
}
