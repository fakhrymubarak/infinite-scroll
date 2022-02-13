package com.fakhry.paging.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieData(

    @field:SerializedName("id")
    val movieId: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null
)