package com.test.domain

import com.test.domain.dto.Log
import com.test.domain.dto.NaverMovie
import com.test.domain.dto.RandomUser
import io.reactivex.Single
import retrofit2.Response

interface AppDataSource {

    fun getRandomUser(queryMap: Map<String, String>, url: String) : Single<RandomUser>

    suspend fun getMovie(headerMap: Map<String, String>, queryMap: Map<String, String>) : Response<NaverMovie>

    suspend fun addLog(msg: String)

    suspend fun getAllLogs(): List<Log>

    suspend fun removeLogs()

    suspend fun getLastLog(): Log?
}