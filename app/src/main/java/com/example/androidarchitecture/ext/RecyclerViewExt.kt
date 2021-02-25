package com.example.androidarchitecture.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.domain.dto.Log
import com.test.domain.dto.MovieItem
import com.test.domain.dto.UserFormat
import com.example.androidarchitecture.ui.base.BaseViewModel
import com.example.androidarchitecture.ui.log.LogAdapter
import com.example.androidarchitecture.ui.moviesearch.MovieAdapter
import com.example.androidarchitecture.ui.moviesearch.MovieSearchViewModel
import com.example.androidarchitecture.ui.users.UserAdapter
import com.example.androidarchitecture.ui.users.UsersViewModel
import com.example.androidarchitecture.util.EndlessPageRecyclerViewScrollListener
import com.example.androidarchitecture.util.EndlessRecyclerViewScrollListener

@BindingAdapter("setUserRecyclerViewAttr")
fun RecyclerView.setUserRecyclerViewAttr(viewModel: BaseViewModel) {
    if (viewModel is UsersViewModel) {
        val layoutManager = LinearLayoutManager(context)
        adapter = UserAdapter(viewModel)
        this.layoutManager = layoutManager
        addOnScrollListener(object :
            EndlessPageRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.loadMore()
            }
        })
    }
}

@BindingAdapter("setUserRecyclerViewAttrTest")
fun RecyclerView.setUserRecyclerViewAttrTest(viewModel: BaseViewModel) {
    if (viewModel is UsersViewModel) {
        val layoutManager = LinearLayoutManager(context)
        adapter = UserAdapter(viewModel)
        this.layoutManager = layoutManager
        addOnScrollListener(
            EndlessRecyclerViewScrollListener(
                layoutManager,
                8,
                viewModel.loadMoreTest
            )
        )
    }
}

@BindingAdapter("setUserData")
fun RecyclerView.setUserData(userFormats: List<UserFormat>?) {
    val userAdapter = adapter as? UserAdapter
    userFormats?.let { userAdapter?.setData(it) }
}

@BindingAdapter("setMovieRecyclerViewAttr")
fun RecyclerView.setMovieRecyclerViewAttr(viewModel: BaseViewModel) {
    if (viewModel is MovieSearchViewModel) {
        val layoutManager = LinearLayoutManager(context)
        adapter = MovieAdapter(viewModel)
        this.layoutManager = layoutManager
        addOnScrollListener(
            EndlessRecyclerViewScrollListener(
                layoutManager,
                8,
                viewModel.loadMoreTest
            )
        )
    }
}

@BindingAdapter("setMovieData")
fun RecyclerView.setMovieData(movieItems: List<MovieItem>?) {
    val movieAdapter = adapter as? MovieAdapter
    movieItems?.let { movieAdapter?.replaceAllItem(it) }
}

@BindingAdapter("setLogData")
fun RecyclerView.setLogData(logs: List<Log>?) {
    val logAdapter = adapter as? LogAdapter ?: LogAdapter()
    if (adapter == null) {
        adapter = logAdapter
    }
    logs?.let { logAdapter.setData(it) }
}