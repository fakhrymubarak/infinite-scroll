package com.fakhry.paging.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val data: List<MovieData>,

	@field:SerializedName("total_results")
	val totalResults: Int
)