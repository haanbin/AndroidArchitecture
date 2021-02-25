package com.test.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteAllLogUseCase @Inject constructor(private val appRepository: AppDataSource) {

    suspend operator fun invoke(){
        appRepository.removeLogs()
    }
}