package com.example.androidarchitecture.ui.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.domain.DeleteAllLogUseCase
import com.example.androidarchitecture.domain.GetLogsUseCase
import com.example.androidarchitecture.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LogViewModel @Inject constructor(
    private val deleteAllLogUseCase: DeleteAllLogUseCase,
    private val getLogsUseCase: GetLogsUseCase
) :
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
            val logs = getLogsUseCase()
            withContext(Dispatchers.Main) {
                _logs.value = logs
                _loading.value = false
            }
        }
    }

    private fun removeLog() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllLogUseCase()
            withContext(Dispatchers.Main) {
                _showToastEvent.value = Event("Clear all log")
                getLog()
            }
        }
    }
}
