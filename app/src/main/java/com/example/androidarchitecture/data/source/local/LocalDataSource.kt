package com.example.androidarchitecture.data.source.local

import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.data.entities.RandomUser
import io.reactivex.Single

interface LocalDataSource {

    suspend fun addLog(msg: String)

    suspend fun getAllLogs(): List<Log>

    suspend fun removeLogs()

    suspend fun getLastLog(): Log?
}