package com.fakhry.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.paging.R
import com.fakhry.paging.data.source.remote.response.MovieData
import com.fakhry.paging.databinding.ItemMovieBinding

class MovieAdapter(private val listMovie: List<MovieData>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onItemClick: ((MovieData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )

    override fun getItemCount() = listMovie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[layoutPosition])
            }
        }

        fun bind(data: MovieData) {
            val releaseYear = data.releaseDate?.substring(0, 4)
            with(binding){
                tvTitle.text = "$releaseYear - ${data.title}"
            }
        }
    }
}
