package com.example.androidarchitecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidarchitecture.data.entities.Log

@Database(entities = arrayOf(Log::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun logDao(): LogDao
}
