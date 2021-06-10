package com.example.androidarchitecture.ui.moviesearch

import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import com.example.androidarchitecture.BR
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityMovieSearchBinding
import com.example.androidarchitecture.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchActivity :
    BaseActivity<ActivityMovieSearchBinding, MovieSearchViewModel>(R.layout.activity_movie_search) {

    override val viewModel: MovieSearchViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.viewModel

    override fun start() {
        onObserve()
    }

    private fun onObserve() {
        viewModel.clearEvent.observe(
            this,
            { event ->
                event.getContentIfNotHandled()?.let {
                    (binding.recyclerViewMovieSearch.adapter as MovieAdapter).clearItem()
                }
            }
        )
        viewModel.showToastEvent.observe(
            this,
            { event ->
                event.getContentIfNotHandled()?.let {
                    showToast(it)
                }
            }
        )
        viewModel.openLinkEvent.observe(
            this,
            { event ->
                event.getContentIfNotHandled()?.let {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                }
            }
        )
    }
}
