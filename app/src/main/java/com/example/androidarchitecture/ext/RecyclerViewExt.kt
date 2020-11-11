package com.example.androidarchitecture.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.data.entities.UserFormat
import com.example.androidarchitecture.ui.users.UserAdapter
import com.example.androidarchitecture.ui.users.UsersViewModel
import com.example.androidarchitecture.util.EndlessRecyclerViewScrollListener

@BindingAdapter("setUserRecyclerViewAttr")
fun RecyclerView.setUserRecyclerViewAttr(viewModel: UsersViewModel) {
    val layoutManager = LinearLayoutManager(context)
    adapter = UserAdapter(viewModel)
    this.layoutManager = layoutManager
    addOnScrollListener(object :
        EndlessRecyclerViewScrollListener(layoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            viewModel.loadMore(page)
        }
    })
}

@BindingAdapter("setUserData")
fun RecyclerView.setUserData(userFormats: List<UserFormat>?) {
    val userAdapter = adapter as? UserAdapter
    userFormats?.let { userAdapter?.setData(it) }
}

