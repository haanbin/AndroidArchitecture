package com.example.androidarchitecture.vo

import com.test.domain.dto.LogDto

data class LogVo(var id: Long, val msg: String, val timestamp: Long)


fun List<LogDto>.toPresenter(): List<LogVo> {
    return map {
        LogVo(it.id, it.msg, it.timestamp)
    }
}