package com.example.androidarchitecture.ui.log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.databinding.ItemLogBinding

class LogAdapter : ListAdapter<Log, LogAdapter.LogViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding: ItemLogBinding =
            ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    fun setData(newList: List<Log>) {
        submitList(newList)
    }

    inner class LogViewHolder(private val binding: ItemLogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(log: Log) {
            with(binding) {
                model = log
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Log>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: Log,
                newConcert: Log
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: Log,
                newConcert: Log
            ) = oldConcert == newConcert
        }
    }
}