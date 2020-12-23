package com.example.androidarchitecture.data.source.remote

import com.example.androidarchitecture.data.entities.RandomUser
import io.reactivex.Single

interface RemoteDataSource {

    fun getRandomUser(queryMap: Map<String, String>, url: String) : Single<RandomUser>

}