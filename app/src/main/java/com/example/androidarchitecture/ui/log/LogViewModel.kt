package com.example.androidarchitecture.ui.log

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.ui.base.BaseViewModel
import com.example.androidarchitecture.vo.LogVo
import com.example.androidarchitecture.vo.toPresenter
import com.test.domain.DeleteAllLogUseCase
import com.test.domain.GetLogsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogViewModel @ViewModelInject constructor(
    private val deleteAllLogUseCase: DeleteAllLogUseCase,
    private val getLogsUseCase: GetLogsUseCase
) :
    BaseViewModel() {

    private val _logs = MutableLiveData<List<LogVo>>()
    val logs: LiveData<List<LogVo>> = _logs
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
            val logs = getLogsUseCase().toPresenter()
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