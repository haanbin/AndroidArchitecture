package com.example.androidarchitecture.ui.home

import android.content.Intent
import androidx.activity.viewModels
import com.example.androidarchitecture.BR
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityHomeBinding
import com.example.androidarchitecture.ui.base.BaseActivity
import com.example.androidarchitecture.ui.log.LogActivity
import com.example.androidarchitecture.ui.moviesearch.MovieSearchActivity
import com.example.androidarchitecture.ui.randommain.RandomMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home) {

    override val viewModel: HomeViewModel by viewModels()

    override val viewModelVariable: Int
        get() = BR.viewModel

    override fun start() {
        onObserve()
    }

    private fun onObserve() {
        viewModel.randomEvent.observe(
            this,
            {
                it.getContentIfNotHandled()?.let {
                    Intent(this, RandomMainActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        )
        viewModel.movieSearchEvent.observe(
            this,
            {
                it.getContentIfNotHandled()?.let {
                    Intent(this, MovieSearchActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        )
        viewModel.logEvent.observe(
            this,
            {
                it.getContentIfNotHandled()?.let {
                    Intent(this, LogActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        )
    }
}
