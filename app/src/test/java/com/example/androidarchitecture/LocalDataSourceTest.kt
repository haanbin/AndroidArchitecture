package com.example.androidarchitecture

import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.data.source.local.LocalDataSource
import com.example.androidarchitecture.data.source.local.LocalDataSourceImpl
import com.example.androidarchitecture.data.source.local.LogDao
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LocalDataSourceTest {

    private val logDao: LogDao = mockk(relaxed = true)
    private lateinit var localDataSource: LocalDataSource

    @BeforeEach
    fun setup() {
        localDataSource = LocalDataSourceImpl(logDao)
    }

    @Test
    @DisplayName("로그 삭제")
    fun `로그 삭제`() = runBlocking {

        localDataSource.removeLogs()
        coVerify {
            logDao.nukeTable()
        }
    }

    @Test
    @DisplayName("전체 로그 가져오기")
    fun `전체 로그 가져오기`() = runBlocking {
        localDataSource.getAllLogs()
        coVerify {
            logDao.getAll()
        }
    }

    @Test
    @DisplayName("로그 입력하기")
    fun `로그 입력하기`() = runBlocking {
        val log = Log("TEST", System.currentTimeMillis())
        localDataSource.addLog("TEST")
        coVerify {
            logDao.insertAll(log)
        }
    }
}
