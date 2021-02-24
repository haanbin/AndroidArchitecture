package com.example.androidarchitecture.domain

import com.example.androidarchitecture.data.source.AppRepository
import javax.inject.Inject

class DeleteAllLogUseCase @Inject constructor(private val appRepository: AppRepository) {

    suspend operator fun invoke(){
        appRepository.removeLogs()
    }
}