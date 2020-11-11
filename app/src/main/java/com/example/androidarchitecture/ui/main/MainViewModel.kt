package com.example.androidarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.data.source.AppRepository
import com.example.androidarchitecture.ui.base.BaseViewModel

class MainViewModel(
    private val appRepository: AppRepository
) : BaseViewModel() {

    private val queryMap = HashMap<String, String>()

    private val _randomEvent = MutableLiveData<Event<Unit>>()
    val randomEvent: LiveData<Event<Unit>>
        get() = _randomEvent
    private val _usersEvent = MutableLiveData<Event<Unit>>()
    val usersEvent: LiveData<Event<Unit>>
        get() = _usersEvent

    fun randomCall() {
        _randomEvent.value = Event(Unit)
    }

    fun usersCall() {
        _usersEvent.value = Event(Unit)
    }
}