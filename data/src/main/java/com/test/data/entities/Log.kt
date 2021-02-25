package com.test.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.domain.dto.LogDto

@Entity(tableName = "LOG")
data class Log(val msg: String, val timestamp: Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun Log.toDomain(): LogDto {
    return LogDto(
        id = id,
        timestamp = timestamp,
        msg = msg
    )
}

fun List<Log>.toDomain(): List<LogDto> {
    return map {
        LogDto(
            id = it.id,
            timestamp = it.timestamp,
            msg = it.msg
        )
    }
}



