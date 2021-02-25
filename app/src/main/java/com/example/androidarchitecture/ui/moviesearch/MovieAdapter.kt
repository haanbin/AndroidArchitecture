package com.example.androidarchitecture.ui.moviesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.domain.dto.MovieItem
import com.example.androidarchitecture.databinding.ItemMovieBinding

class MovieAdapter(movieSearchViewModel: MovieSearchViewModel) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val list: ArrayList<MovieItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun replaceAllItem(newList: List<MovieItem>) {
        list.run {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    fun clearItem() {
        list.clear()
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(movieItem: MovieItem) {
            with(binding) {
                model = movieItem
                executePendingBindings()
            }
        }
    }
}