package com.example.androidarchitecture.ui.random

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityRandomBinding
import com.example.androidarchitecture.ext.obtainViewModel
import com.example.androidarchitecture.ui.base.BaseActivity

class RandomActivity :
    BaseActivity<ActivityRandomBinding, RandomViewModel>(R.layout.activity_random) {

    override val viewModel: RandomViewModel
        get() = obtainViewModel(RandomViewModel::class.java)

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