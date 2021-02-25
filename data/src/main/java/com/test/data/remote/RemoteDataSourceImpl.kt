package com.test.data.remote

import com.example.androidarchitecture.data.api.RetrofitService
import com.test.domain.model.NaverMovie
import com.test.domain.model.RandomUser
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService) :
    RemoteDataSource {

    override fun getRandomUser(queryMap: Map<String, String>, url: String): Single<RandomUser> =
        retrofitService.getRandomUser(url, queryMap)
            .subscribeOn(Schedulers.io())

    override suspend fun getMovie(
        headerMap: Map<String, String>,
        queryMap: Map<String, String>
    ): Response<NaverMovie> =
        retrofitService.getMovie(headerMap, queryMap)
}