package com.example.androidarchitecture.domain

import com.example.androidarchitecture.data.entities.NaverMovie
import com.example.androidarchitecture.data.source.AppRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieUseCase @Inject constructor(
    private val appRepository: AppRepository
) {

    suspend operator fun invoke(
        headerMap: Map<String, String>,
        queryMap: Map<String, String>
    ): Response<NaverMovie> =
        appRepository.getMovie(headerMap, queryMap)


}