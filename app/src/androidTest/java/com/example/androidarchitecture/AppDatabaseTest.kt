package com.example.androidarchitecture

import android.os.SystemClock
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.data.source.local.AppDatabase
import com.example.androidarchitecture.data.source.local.LogDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var logDao: LogDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
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