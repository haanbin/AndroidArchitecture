package com.test.data.entities

import com.google.gson.annotations.SerializedName
import com.test.domain.dto.MovieItem

data class NaverMovie(
    val display: Int,
    @SerializedName("items")
    val movieItems: List<MovieItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)