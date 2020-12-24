package com.example.androidarchitecture.ui.moviesearch

import androidx.activity.viewModels
import com.example.androidarchitecture.BR
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityMovieSearchBinding
import com.example.androidarchitecture.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchActivity : BaseActivity<ActivityMovieSearchBinding, MovieSearchViewModel>(R.layout.activity_movie_search) {

    override val viewModel: MovieSearchViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.viewModel

    override fun start() {

    }

}