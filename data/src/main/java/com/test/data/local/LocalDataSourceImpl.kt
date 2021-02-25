package com.test.data.local

import com.test.data.entities.Log
import com.test.data.entities.toDomain
import com.test.domain.dto.LogDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val logDao: LogDao
) : LocalDataSource {

    override suspend fun addLog(msg: String) {
        logDao.insertAll(
            Log(
                msg,
                System.currentTimeMillis()
            )
        )
    }

    override suspend fun getAllLogs(): List<LogDto> {
        return logDao.getAll().toDomain()
    }

    override suspend fun removeLogs() {
        logDao.nukeTable()
    }

    override suspend fun getLastLog(): LogDto? {
        return logDao.getLastLog()?.toDomain()
    }
}