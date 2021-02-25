package com.example.androidarchitecture.entities

import com.test.domain.dto.Info
import com.test.domain.dto.Result

data class RandomUser(
    val info: Info,
    val results: List<Result>
)