package com.example.androidarchitecture.ui.moviesearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.BuildConfig
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.data.entities.MovieItem
import com.example.androidarchitecture.data.source.AppRepository
import com.example.androidarchitecture.domain.GetMovieUseCase
import com.example.androidarchitecture.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieSearchViewModel @ViewModelInject constructor(private val getMovieUseCase: GetMovieUseCase) :
    BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading
    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> = _showToastEvent
    private val _clearEvent = MutableLiveData<Event<Unit>>()
    val clearEvent: LiveData<Event<Unit>> = _clearEvent
    private val _movieItems = MutableLiveData<List<MovieItem>>()
    val movieItems: LiveData<List<MovieItem>> = _movieItems
    private val _openLinkEvent = MutableLiveData<Event<String>>()
    val openLinkEvent: LiveData<Event<String>> = _openLinkEvent

    val searchText = MutableLiveData<String>()
    private val display = 10
    private var total = 0;
    private val queryMap = HashMap<String, String>().apply {
        put("display", display.toString())
        put("start", "1")
        put("query", "")
    }
    private val headerMap = HashMap<String, String>().apply {
        put("X-Naver-Client-Id", BuildConfig.clientId)
        put("X-Naver-Client-Secret", BuildConfig.clientSecret)
    }

    fun searchClicked() {
        searchText.value?.let {
            if (it.trim().isEmpty()) {
                _showToastEvent.value = Event("검색어를 입력해주세요.")
                return
            }
            _movieItems.value = listOf()
            queryMap["query"] = it
            getNaverMovie()
        }
    }

    fun onMovieItemClicked(link: String) {
        _openLinkEvent.value = Event(link)
    }

    val loadMoreTest = {
        val start = queryMap["start"]?.toInt() ?: 0
        if ((start + display) <= total) {
            queryMap["start"] = (start + display).toString()
            getNaverMovie()
        }
    }

    fun loadMore() {
        val start = queryMap["start"]?.toInt()
        start?.let {
            if ((it + display) > total) {
                return
            }
            queryMap["start"] = (it + display).toString()
            getNaverMovie()
        }
    }

    private fun getNaverMovie() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getMovieCall()
                if (response.isSuccessful) {
                    response.body()?.let { naverMovie ->
                        total = naverMovie.total
                        if (naverMovie.movieItems.isEmpty()) {
                            withContext(Dispatchers.Main) {
                                _showToastEvent.value = Event("검색결과가 없습니다.")
                                _clearEvent.value = Event(Unit)
                                _loading.value = false
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                val itemList = (_movieItems.value ?: listOf()).toMutableList()
                                itemList.addAll(naverMovie.movieItems)
                                _movieItems.value = itemList
                                _loading.value = false
                            }
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _showToastEvent.value = Event("검색에 실패했습니다.")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _showToastEvent.value = Event("검색에 실패했습니다.")
                }
            }
        }
    }

    private suspend fun getMovieCall() = getMovieUseCase(headerMap, queryMap)

}