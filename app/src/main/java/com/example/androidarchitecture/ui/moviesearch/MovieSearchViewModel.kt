package com.example.androidarchitecture.ui.moviesearch

import androidx.hilt.lifecycle.ViewModelInject
import com.example.androidarchitecture.BuildConfig
import com.example.androidarchitecture.ui.base.BaseViewModel

class MovieSearchViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val queryMap = HashMap<String, String>()
    private val headerMap = HashMap<String, String>().apply {
        put("X-Naver-Client-Id", BuildConfig.clientId)
        put("X-Naver-Client-Secret", BuildConfig.clientSecret)
    }
}