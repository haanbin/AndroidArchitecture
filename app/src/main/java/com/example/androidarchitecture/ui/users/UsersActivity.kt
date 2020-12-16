package com.example.androidarchitecture.ui.users

import androidx.lifecycle.Observer
import com.example.androidarchitecture.BR
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityUsersBinding
import com.example.androidarchitecture.ext.obtainViewModel
import com.example.androidarchitecture.ui.base.BaseActivity

class UsersActivity : BaseActivity<ActivityUsersBinding, UsersViewModel>(R.layout.activity_users) {

    override val viewModel: UsersViewModel
        get() = obtainViewModel(UsersViewModel::class.java)

    override val viewModelVariable: Int
        get() = BR.viewModel

    override fun start() {
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