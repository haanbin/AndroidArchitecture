package com.test.domain

import com.test.domain.model.RandomUser
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRandomUsersUseCase @Inject constructor(private val appRepository: AppDataSource) {

    operator fun invoke(queryMap: Map<String, String>, url: String): Single<RandomUser> =
        appRepository.getRandomUser(queryMap, url)

}