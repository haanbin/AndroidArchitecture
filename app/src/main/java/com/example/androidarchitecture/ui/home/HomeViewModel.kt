package com.example.androidarchitecture.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.data.source.AppRepository
import com.example.androidarchitecture.domain.SaveLogUseCase
import com.example.androidarchitecture.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val saveLogUseCase: SaveLogUseCase) : BaseViewModel() {

    private val _randomEvent = MutableLiveData<Event<Unit>>()
    val randomEvent: LiveData<Event<Unit>>
        get() = _randomEvent

    private val _movieSearchEvent = MutableLiveData<Event<Unit>>()
    val movieSearchEvent: LiveData<Event<Unit>>
        get() = _movieSearchEvent

    private val _logEvent = MutableLiveData<Event<Unit>>()
    val logEvent: LiveData<Event<Unit>>
        get() = _logEvent

    fun onRandomClick() {
        viewModelScope.launch(Dispatchers.IO) {
            saveLogUseCase("RANDOM CLICK")
        }
        _randomEvent.value = Event(Unit)
    }

    fun onMovieSearchClick() {
        viewModelScope.launch(Dispatchers.IO) {
            saveLogUseCase("MOVIE SEARCH CLICK")
        }
        _movieSearchEvent.value = Event(Unit)
    }

    fun onLogClick(){
        _logEvent.value = Event(Unit)
    }
}