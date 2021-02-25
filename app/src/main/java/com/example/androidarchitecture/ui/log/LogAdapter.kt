package com.example.androidarchitecture.ui.log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.databinding.ItemLogBinding
import com.example.androidarchitecture.vo.LogVo

class LogAdapter : ListAdapter<LogVo, LogAdapter.LogViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val binding: ItemLogBinding =
            ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    fun setData(newList: List<LogVo>) {
        submitList(newList)
    }

    inner class LogViewHolder(private val binding: ItemLogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(logDto: LogVo) {
            with(binding) {
                model = logDto
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<LogVo>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: LogVo,
                newConcert: LogVo
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: LogVo,
                newConcert: LogVo
            ) = oldConcert == newConcert
        }
    }
}