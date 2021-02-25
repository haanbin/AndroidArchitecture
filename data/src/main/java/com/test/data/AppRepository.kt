package com.test.data

import com.test.domain.dto.Log
import com.test.domain.dto.NaverMovie
import com.test.domain.dto.RandomUser
import com.test.data.local.LocalDataSourceImpl
import com.test.data.remote.RemoteDataSourceImpl
import com.test.domain.AppDataSource
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl
) : AppDataSource {

    override fun getRandomUser(queryMap: Map<String, String>, url: String): Single<RandomUser> {
        return remoteDataSourceImpl.getRandomUser(queryMap, url)
    }

    override suspend fun getMovie(
        headerMap: Map<String, String>,
        queryMap: Map<String, String>
    ): Response<NaverMovie> {
        return remoteDataSourceImpl.getMovie(headerMap, queryMap)
    }

    override suspend fun addLog(msg: String) {
        localDataSourceImpl.addLog(msg)
    }

    override suspend fun getAllLogs(): List<Log> = localDataSourceImpl.getAllLogs()

    override suspend fun removeLogs() {
        localDataSourceImpl.removeLogs()
    }

    override suspend fun getLastLog(): Log? = localDataSourceImpl.getLastLog()

}