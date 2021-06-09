package com.example.androidarchitecture

import com.test.data.local.AppDatabase
import com.test.data.local.LogDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class AppDatabaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase
    lateinit var logDao: LogDao

    @Before
    fun createDb() {
        hiltRule.inject()
        logDao = appDatabase.logDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun insertLog() = runBlocking {
//        val log = LogDto("TEST LOG", SystemClock.currentThreadTimeMillis())
//        logDao.insertAll(log)
//        val logs = logDao.getLastLog()
//        assertEquals(logs?.msg, "TEST LOG")
    }
}