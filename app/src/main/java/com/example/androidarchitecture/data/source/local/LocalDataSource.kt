package com.example.androidarchitecture.data.source.local

import com.example.androidarchitecture.data.entities.RandomUser
import io.reactivex.Single

interface LocalDataSource {

    fun getRandomUser(queryMap: Map<String, String>, url: String) : Single<RandomUser>

}