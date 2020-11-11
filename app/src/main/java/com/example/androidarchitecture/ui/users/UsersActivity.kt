package com.example.androidarchitecture.ui.users

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityUsersBinding
import com.example.androidarchitecture.ext.obtainViewModel
import com.example.androidarchitecture.ui.base.BaseActivity

class UsersActivity : BaseActivity<ActivityUsersBinding, UsersViewModel>(R.layout.activity_users) {

    override val viewModel: UsersViewModel
        get() = obtainViewModel(UsersViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        onObserve()
    }

    private fun onObserve() {
        viewModel.toastMessage.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                showToast(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }
}