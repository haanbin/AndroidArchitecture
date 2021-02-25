package com.example.androidarchitecture.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.domain.dto.UserFormat
import com.example.androidarchitecture.databinding.ItemUserBinding

class UserAdapter(private val viewModel: UsersViewModel) :
    ListAdapter<UserFormat, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userFormat: UserFormat? = getItem(position)
        userFormat?.let {
            holder.bindTo(userFormat, viewModel)
        }
    }

    fun setData(newList: List<UserFormat>) {
        submitList(newList)
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(userFormat: UserFormat, viewModel: UsersViewModel) {
            binding.userFormat = userFormat
            binding.viewModel = viewModel
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<UserFormat>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: UserFormat,
                newConcert: UserFormat
            ) = oldConcert.uuid == newConcert.uuid

            override fun areContentsTheSame(
                oldConcert: UserFormat,
                newConcert: UserFormat
            ) = oldConcert == newConcert
        }
    }
}