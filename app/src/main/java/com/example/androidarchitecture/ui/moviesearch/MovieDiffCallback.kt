package com.example.androidarchitecture.ui.moviesearch

import androidx.recyclerview.widget.DiffUtil
import com.example.androidarchitecture.data.entities.MovieItem

class MovieDiffCallback(
    private val oldList: List<MovieItem>,
    private val newList: List<MovieItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title == newList[newItemPosition].title
}
