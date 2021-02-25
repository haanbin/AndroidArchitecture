package com.test.data.local

import com.test.data.mapper.LogEntityMapper
import com.test.domain.dto.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val logDao: LogDao
) : LocalDataSource {

    override suspend fun addLog(msg: String) {
        logDao.insertAll(
            com.test.data.entities.Log(
                msg,
                System.currentTimeMillis()
            )
        )
    }

    override suspend fun getAllLogs(): List<Log> {
        return logDao.getAll().run {
            this.asSequence().map {
                LogEntityMapper.toDomain(it)
            }.toList()
        }
    }

    override suspend fun removeLogs() {
        logDao.nukeTable()
    }

    override suspend fun getLastLog(): Log?  {
        return logDao.getLastLog()?.let {
            LogEntityMapper.toDomain(it)
        }
    }
}