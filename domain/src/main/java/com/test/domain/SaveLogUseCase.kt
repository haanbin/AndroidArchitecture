package com.test.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveLogUseCase @Inject constructor(private val appRepository: AppDataSource) {

    suspend operator fun invoke(msg: String) {
        appRepository.addLog(msg)
    }
}