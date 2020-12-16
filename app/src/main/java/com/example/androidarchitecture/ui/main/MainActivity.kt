package com.example.androidarchitecture.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.androidarchitecture.BR
import com.example.androidarchitecture.R
import com.example.androidarchitecture.data.api.RetrofitClient
import com.example.androidarchitecture.data.entities.Result
import com.example.androidarchitecture.databinding.ActivityMainBinding
import com.example.androidarchitecture.ext.obtainViewModel
import com.example.androidarchitecture.ui.base.BaseActivity
import com.example.androidarchitecture.ui.random.RandomActivity
import com.example.androidarchitecture.ui.users.UsersActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel
        get() = obtainViewModel(MainViewModel::class.java)

    override val viewModelVariable: Int
        get() = BR.viewModel

    override fun start() {
        onObserve()

    }

    private fun onObserve() {
        with(viewModel) {
            randomEvent.observe(this@MainActivity, Observer {
                it.getContentIfNotHandled()?.let {
                    openRandom()
                }
            })
            usersEvent.observe(this@MainActivity, Observer {
                it.getContentIfNotHandled()?.let {
                    openUsers()
                }
            })
        }
    }

    private fun openRandom() {
        Intent(this, RandomActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun openUsers() {
        Intent(this, UsersActivity::class.java).apply {
            startActivity(this)
        }
    }

}