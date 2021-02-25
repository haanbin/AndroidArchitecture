package com.test.data.remote

import com.test.domain.model.NaverMovie
import com.test.domain.model.RandomUser
import io.reactivex.Single
import retrofit2.Response

interface RemoteDataSource {

    fun getRandomUser(queryMap: Map<String, String>, url: String) : Single<RandomUser>

    suspend fun getMovie(headerMap: Map<String, String>, queryMap: Map<String, String>) : Response<NaverMovie>
}