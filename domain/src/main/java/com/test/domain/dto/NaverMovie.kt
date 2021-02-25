package com.test.domain.dto

import com.google.gson.annotations.SerializedName

data class NaverMovie(
    val display: Int,
    @SerializedName("items")
    val movieItems: List<MovieItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)