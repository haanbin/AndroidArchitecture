package com.example.androidarchitecture

import android.os.SystemClock
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.data.source.local.AppDatabase
import com.example.androidarchitecture.data.source.local.LogDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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
        val log = Log("TEST LOG", SystemClock.currentThreadTimeMillis())
        logDao.insertAll(log)
        val logs = logDao.getLastLog()
        assertEquals(logs?.msg, "TEST LOG")
    }


}