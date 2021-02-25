package com.example.androidarchitecture.entities

import com.test.domain.dto.Dob
import com.test.domain.dto.Id
import com.test.domain.dto.Location
import com.test.domain.dto.Login
import com.test.domain.dto.Name
import com.test.domain.dto.Picture
import com.test.domain.dto.Registered

data class Result(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)