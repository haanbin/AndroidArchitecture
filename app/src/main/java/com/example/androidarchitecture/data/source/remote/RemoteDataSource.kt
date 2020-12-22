package com.example.androidarchitecture.data.source.remote

import com.example.androidarchitecture.data.api.RetrofitService
import com.example.androidarchitecture.data.entities.RandomUser
import com.example.androidarchitecture.data.source.AppDataSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor() : AppDataSource {

    @Inject
    lateinit var retrofitService: RetrofitService

    override fun getRandomUser(queryMap: Map<String, String>): Single<RandomUser> =
        retrofitService.getRandomUser(queryMap)
            .subscribeOn(Schedulers.io())

}