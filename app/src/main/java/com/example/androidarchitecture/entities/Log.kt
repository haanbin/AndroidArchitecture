package com.example.androidarchitecture.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LOG")
data class Log(val msg: String, val timestamp: Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}