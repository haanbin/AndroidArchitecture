package com.example.androidarchitecture.ui.moviesearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.BuildConfig
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.data.entities.MovieItem
import com.example.androidarchitecture.domain.GetMovieUseCase
import com.example.androidarchitecture.domain.SaveLogUseCase
import com.example.androidarchitecture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val saveLogUseCase: SaveLogUseCase
) :
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

    // two way binding
    val searchText = MutableLiveData<String>()
    private val display = 10
    private var total = 0
    private val queryMap = HashMap<String, String>().apply {
        put(DISPLAY, display.toString())
        put(START, "1")
        put(QUERY, "")
    }
    private val headerMap = HashMap<String, String>().apply {
        put("X-Naver-Client-Id", BuildConfig.clientId)
        put("X-Naver-Client-Secret", BuildConfig.clientSecret)
    }
    val loadMoreTest = {
        val start = queryMap[START]?.toInt() ?: 0
        if ((start + display) <= total) {
            queryMap[START] = (start + display).toString()
            getNaverMovie()
        }
    }

    fun searchClicked() {
        searchText.value?.let {
            if (it.trim().isEmpty()) {
                _showToastEvent.value = Event("검색어를 입력해주세요.")
                return
            }
            _movieItems.value = listOf()
            queryMap[QUERY] = it
            saveKeywordLog(it)
            getNaverMovie()
        }
    }

    fun onMovieItemClicked(link: String) {
        _openLinkEvent.value = Event(link)
    }

    fun loadMore() {
        val start = queryMap[START]?.toInt()
        start?.let {
            if ((it + display) > total) {
                return
            }
            queryMap[START] = (it + display).toString()
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

    private fun saveKeywordLog(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveLogUseCase("SEARCH MOVIE KEYWORD : $keyword")
        }
    }

    private suspend fun getMovieCall() = getMovieUseCase(headerMap, queryMap)

    companion object {

        const val START = "start"
        const val QUERY = "query"
        const val DISPLAY = "display"
    }
}
