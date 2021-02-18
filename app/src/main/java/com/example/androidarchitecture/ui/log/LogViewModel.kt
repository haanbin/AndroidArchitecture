package com.example.androidarchitecture.ui.log

import android.util.Log.d
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.data.source.AppRepository
import com.example.androidarchitecture.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogViewModel @ViewModelInject constructor(private val repository: AppRepository) :
    BaseViewModel() {

    private val _logs = MutableLiveData<List<Log>>()
    val logs: LiveData<List<Log>> = _logs
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> = _showToastEvent

    init {
        getLog()
    }

    fun clearClicked() {
        removeLog()
    }

    private fun getLog() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val logs = repository.getAllLogs()
            android.util.Log.d("123", logs.size.toString())
            withContext(Dispatchers.Main) {
                _logs.value = logs
                _loading.value = false
            }
        }
    }

    private fun removeLog() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeLogs()
            withContext(Dispatchers.Main) {
                _showToastEvent.value = Event("Clear all log")
            }
        }
    }
}