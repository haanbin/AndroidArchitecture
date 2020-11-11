package com.example.androidarchitecture.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidarchitecture.Event
import com.example.androidarchitecture.data.entities.Result
import com.example.androidarchitecture.data.entities.UserFormat
import com.example.androidarchitecture.data.source.AppRepository
import com.example.androidarchitecture.ui.base.BaseViewModel
import com.example.androidarchitecture.ui.random.RandomViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class UsersViewModel(
    private val appRepository: AppRepository
) : BaseViewModel() {

    private val queryMap = HashMap<String, String>()
    private val _userFormats = MutableLiveData<List<UserFormat>>()
    val userFormats: LiveData<List<UserFormat>>
        get() = _userFormats

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>>
        get() = _toastMessage

    init {
        with(queryMap) {
            put("page", "1")
            put("results", "20")
            put("seed", "abc")
        }
    }

    fun start() {
        loadRandomUser()
    }

    fun loadMore(page: Int) {
        queryMap["page"] = page.toString()
        loadRandomUser()
    }

    private fun loadRandomUser() {
        appRepository.getRandomUser(queryMap)
            .map {
                it.results.asSequence()
                    .map { result ->
                        getUserFormat(result)
                    }.toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userFormats.value = it
            }, {
                _toastMessage.value = Event(it.message.toString())
                Log.e(RandomViewModel::javaClass.javaClass.name, it.message.toString())
            }).addTo(compositeDisposable)
    }

    private fun getUserFormat(result: Result): UserFormat {
        val name = "name : ${result.name.first} ${result.name.last}"
        val age = "age : ${result.dob.age}"
        val location = "location : ${result.location.street.number}, " +
                "${result.location.street.name}, ${result.location.city}, " +
                "${result.location.state}, ${result.location.country}"
        return UserFormat(
            result.login.uuid,
            name,
            result.picture.large,
            age,
            location
        )
    }
}