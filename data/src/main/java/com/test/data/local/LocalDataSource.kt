package com.test.data.local

import com.test.domain.dto.LogDto


interface LocalDataSource {

    suspend fun addLog(msg: String)

    suspend fun getAllLogs(): List<LogDto>

    suspend fun removeLogs()

    suspend fun getLastLog(): LogDto?
}