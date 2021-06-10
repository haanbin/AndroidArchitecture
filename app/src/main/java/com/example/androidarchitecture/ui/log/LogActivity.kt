package com.example.androidarchitecture.ui.log

import androidx.activity.viewModels
import com.example.androidarchitecture.BR
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityLogBinding
import com.example.androidarchitecture.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogActivity : BaseActivity<ActivityLogBinding, LogViewModel>(R.layout.activity_log) {

    override val viewModel: LogViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.viewModel

    override fun start() {
        onObserve()
    }

    private fun onObserve() {
        viewModel.run {
            showToastEvent.observe(
                this@LogActivity,
                { event ->
                    event.getContentIfNotHandled()?.let {
                        showToast(it)
                    }
                }
            )
        }
    }
}
