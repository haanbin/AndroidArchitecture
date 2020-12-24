package com.example.androidarchitecture.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.ui.base.BaseViewModel

class HomeViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val _randomEvent = MutableLiveData<Event<Unit>>()
    val randomEvent: LiveData<Event<Unit>>
        get() = _randomEvent

    private val _movieSearchEvent = MutableLiveData<Event<Unit>>()
    val movieSearchEvent: LiveData<Event<Unit>>
        get() = _movieSearchEvent

    fun onRandomClick() {
        _randomEvent.value = Event(Unit)
    }

    fun onMovieSearchClick() {
        _movieSearchEvent.value = Event(Unit)
    }
}