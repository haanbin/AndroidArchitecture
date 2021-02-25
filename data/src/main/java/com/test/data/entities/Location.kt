package com.test.data.entities

import com.test.domain.dto.Coordinates
import com.test.domain.dto.Street
import com.test.domain.dto.Timezone

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
)