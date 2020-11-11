package com.example.androidarchitecture.data.api

import com.example.androidarchitecture.data.entities.RandomUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RetrofitService {

    @GET("api/")
    fun getRandomUser(
        @QueryMap
        queryMap: Map<String, String>
    ): Single<RandomUser>

}