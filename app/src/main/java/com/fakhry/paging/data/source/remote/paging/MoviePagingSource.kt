package com.fakhry.paging.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fakhry.paging.data.source.remote.network.MovieApiService
import com.fakhry.paging.data.source.remote.response.MovieData

private const val TMDB_STARTING_PAGE_INDEX = 1

class MoviePagingSource (
    private val apiService: MovieApiService
) : PagingSource<Int, MovieData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: TMDB_STARTING_PAGE_INDEX
        val response = apiService.getMovPlayings(page = nextPageNumber)
        return LoadResult.Page(
            data = response.data,
            prevKey = null, // Only paging forward.
            nextKey = if (response.page == response.totalPages) null else response.page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}