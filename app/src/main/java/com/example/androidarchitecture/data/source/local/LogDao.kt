package com.example.androidarchitecture.data.source.local

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidarchitecture.data.entities.Log

@Dao
interface LogDao {

    @Query("SELECT * FROM LOG ORDER BY id DESC")
    suspend fun getAll(): List<Log>

    @Insert
    suspend fun insertAll(vararg logs: Log)

    @Query("DELETE FROM LOG")
    suspend fun nukeTable()

    @Query("SELECT * FROM LOG ORDER BY id DESC LIMIT 1")
    suspend fun getLastLog(): Log?

    @Query("SELECT * FROM LOG ORDER BY id DESC")
    suspend fun selectAllLogsCursor(): List<Log>

    @Query("SELECT * FROM LOG WHERE id = :id")
    suspend fun selectLogById(id: Long): Log?
}