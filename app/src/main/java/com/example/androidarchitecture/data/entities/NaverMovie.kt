package com.example.androidarchitecture.data.entities

data class NaverMovie(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)