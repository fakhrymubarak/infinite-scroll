package com.fakhry.paging.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fakhry.paging.data.MovieRepository
import com.fakhry.paging.data.Resource
import com.fakhry.paging.data.source.remote.response.MovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private var repository: MovieRepository) : ViewModel() {

    private var currentSearchResult: Flow<PagingData<MovieData>>? = null

    fun getMovieRepository(): Flow<Resource<List<MovieData>>> = repository.getMoviePlayings()

    fun getPagingMoviePlayings(): Flow<PagingData<MovieData>>{
        val newResult = repository.getPagingMoviePlayings().cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}