package com.example.androidarchitecture.ui.moviesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.data.entities.MovieItem
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

    fun setItem(newList: List<MovieItem>) {
        val calList = arrayListOf<MovieItem>()
        calList.addAll(list)
        calList.addAll(newList)
        calculateDiff(calList)
    }

    fun clearItem() {
        list.clear()
        calculateDiff(list)
    }

    private fun calculateDiff(newList: List<MovieItem>) {
        val diffResult = DiffUtil.calculateDiff(MovieDiffCallback(list, newList))
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
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