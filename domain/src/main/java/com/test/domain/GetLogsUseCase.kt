package com.test.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLogsUseCase @Inject constructor(private val appRepository: AppDataSource) {

    suspend operator fun invoke() = appRepository.getAllLogs()

}