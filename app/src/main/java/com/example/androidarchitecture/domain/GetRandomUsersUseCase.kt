package com.example.androidarchitecture.domain

import com.example.androidarchitecture.data.entities.RandomUser
import com.example.androidarchitecture.data.source.AppRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRandomUsersUseCase @Inject constructor(private val appRepository: AppRepository) {

    operator fun invoke(queryMap: Map<String, String>, url: String): Single<RandomUser> =
        appRepository.getRandomUser(queryMap, url)

}