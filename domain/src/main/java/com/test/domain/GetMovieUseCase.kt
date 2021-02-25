package com.test.domain

import com.test.domain.dto.NaverMovie
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieUseCase @Inject constructor(
    private val appRepository: AppDataSource
) {

    suspend operator fun invoke(
        headerMap: Map<String, String>,
        queryMap: Map<String, String>
    ): Response<NaverMovie> =
        appRepository.getMovie(headerMap, queryMap)


}