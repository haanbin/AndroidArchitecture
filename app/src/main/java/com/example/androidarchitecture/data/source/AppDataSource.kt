package com.example.androidarchitecture.data.source

import com.example.androidarchitecture.data.entities.RandomUser
import io.reactivex.Single

interface AppDataSource {

    fun getRandomUser(queryMap: Map<String, String>, url: String) : Single<RandomUser>

}