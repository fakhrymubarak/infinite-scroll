package com.fakhry.paging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fakhry.paging.data.source.remote.response.MovieData
import com.fakhry.paging.databinding.ItemMovieBinding
import com.fakhry.paging.di.NetworkConst.BASE_IMAGE

class MoviePagingAdapter :
    PagingDataAdapter<MovieData, MoviePagingAdapter.ViewHolder>(MovieDiffCallback()) {

    var onItemClick: ((MovieData) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MoviePagingAdapter.ViewHolder =
        ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) holder.bind(data)
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val item = getItem(layoutPosition)
                if (item != null) {
                    onItemClick?.invoke(item)
                }
            }
        }

        fun bind(data: MovieData) {
            with(binding) {
                tvTitle.text = data.title
                tvReleaseDate.text = data.releaseDate
                tvVoteAverage.text = data.voteAverage.toString()
                tvOverview.text = data.overview
                imageView.load(BASE_IMAGE + data.posterPath)
            }
        }
    }
}


private class MovieDiffCallback : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.movieId == newItem.movieId
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }
}
