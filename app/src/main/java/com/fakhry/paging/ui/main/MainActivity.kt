package com.fakhry.paging.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fakhry.paging.adapter.MovieAdapter
import com.fakhry.paging.adapter.MoviePagingAdapter
import com.fakhry.paging.data.source.remote.response.MovieData
import com.fakhry.paging.databinding.ActivityMainBinding
import com.fakhry.paging.ui.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val adapter = MoviePagingAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initListener()
        fetchData()
    }

    private fun initListener() {
        adapter.onItemClick = { selectedMovie ->
            showToast(this, "Clicked : ${selectedMovie.title}")
        }
    }

    private fun initView() {
        binding.rvMovie.adapter = adapter
    }

    private fun fetchData() {
//        lifecycleScope.launch {
//            viewModel.getMovieRepository().collect { resource ->
//                when (resource) {
//                    is Resource.Error -> showError(resource.message)
//                    is Resource.Loading -> showLoading(true)
//                    is Resource.Success -> {
//                        if (resource.data != null) {
//                            populateMovie(resource.data)
//                        } else {
//                            showError(resource.message)
//                        }
//                        showLoading(false)
//
//                    }
//                }
//            }
//        }
        lifecycleScope.launch {
            viewModel.getPagingMoviePlayings().collect {
                adapter.submitData(it)
            }
        }
    }

    private fun populateMovie(listMovie: List<MovieData>) {
        val movieAdapter = MovieAdapter(listMovie)
        binding.rvMovie.adapter = movieAdapter

        movieAdapter.onItemClick = { selectedMovie ->
            showToast(this, "Clicked : ${selectedMovie.title}")
        }
    }

    private fun showLoading(state: Boolean) {
        val loadingVisibility = if (state) View.VISIBLE else View.GONE
        binding.progressBar.visibility = loadingVisibility
    }

    private fun showError(message: String?) {
        if (message != null) {
            showToast(this, message)
        } else {
            showToast(this, "Failed get data.")
        }
    }
}