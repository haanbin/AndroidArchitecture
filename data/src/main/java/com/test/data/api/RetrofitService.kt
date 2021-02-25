package com.example.androidarchitecture.data.api

import com.test.domain.model.NaverMovie
import com.test.domain.model.RandomUser
import io.reactivex.Single
import retrofit2.Response
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
    suspend fun getMovie(
        @HeaderMap
        headerMap: Map<String, String>,
        @QueryMap
        queryMap: Map<String, String>
    ): Response<NaverMovie>

}