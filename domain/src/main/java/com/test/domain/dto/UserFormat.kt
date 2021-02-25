package com.test.domain.dto

data class UserFormat(
    val uuid: String,
    val name: String,
    val imageUrl: String,
    val info: String,
    val location: String
)