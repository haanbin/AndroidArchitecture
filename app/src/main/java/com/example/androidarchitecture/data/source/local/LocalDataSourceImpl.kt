package com.example.androidarchitecture.data.source.local

import com.example.androidarchitecture.data.entities.Log
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

    override suspend fun getAllLogs() = logDao.getAll()

    override suspend fun removeLogs() {
        logDao.nukeTable()
    }

    override suspend fun getLastLog(): Log? = logDao.getLastLog()
}
