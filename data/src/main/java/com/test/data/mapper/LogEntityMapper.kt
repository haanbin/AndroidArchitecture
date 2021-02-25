package com.test.data.mapper

import com.test.data.entities.Log

object LogEntityMapper {

    fun toDomain(log: Log): com.test.domain.dto.Log {
        return log.let {
            com.test.domain.dto.Log(
                id = log.id,
                timestamp = log.timestamp,
                msg = log.msg
            )
        }
    }

}