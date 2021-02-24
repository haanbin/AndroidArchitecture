package com.example.androidarchitecture.domain

import com.example.androidarchitecture.data.source.AppRepository
import javax.inject.Inject

class SaveLogUseCase @Inject constructor(private val appRepository: AppRepository) {

    suspend operator fun invoke(msg: String) {
        appRepository.addLog(msg)
    }
}