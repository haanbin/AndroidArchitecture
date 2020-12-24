package com.example.androidarchitecture.data.api

import com.example.androidarchitecture.data.entities.RandomUser
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitService {

    @GET
    fun getRandomUser(
        @Url
        url: String,
        @QueryMap
        queryMap: Map<String, String>
    ): Single<RandomUser>

    @GET("movie.json")
    fun getMovie(
        @HeaderMap
        headerMap: Map<String, String>,
        @QueryMap
        queryMap: Map<String, String>
    ): Single<RandomUser>

}