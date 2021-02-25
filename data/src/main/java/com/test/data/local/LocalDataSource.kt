package com.test.data.local

import com.test.domain.dto.Log


interface LocalDataSource {

    suspend fun addLog(msg: String)

    suspend fun getAllLogs(): List<Log>

    suspend fun removeLogs()

    suspend fun getLastLog(): Log?
}