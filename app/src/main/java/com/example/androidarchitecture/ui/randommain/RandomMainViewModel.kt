package com.example.androidarchitecture.ui.randommain

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.domain.SaveLogUseCase
import com.example.androidarchitecture.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomMainViewModel @ViewModelInject constructor(
    private val saveLogUseCase: SaveLogUseCase
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
        saveRandomLog("RANDOM USER CLICK")
    }

    fun usersCall() {
        _usersEvent.value = Event(Unit)
        saveRandomLog("RANDOM USER LIST CLICK")
    }

    private fun saveRandomLog(msg: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveLogUseCase(msg)
        }
    }
}
