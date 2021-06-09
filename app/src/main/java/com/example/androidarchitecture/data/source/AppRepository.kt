package com.example.androidarchitecture.data.source

import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.data.entities.NaverMovie
import com.example.androidarchitecture.data.entities.RandomUser
import com.example.androidarchitecture.data.source.local.LocalDataSource
import com.example.androidarchitecture.data.source.remote.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AppDataSource {

    override fun getRandomUser(queryMap: Map<String, String>, url: String): Single<RandomUser> {
        return remoteDataSource.getRandomUser(queryMap, url)
    }

    override suspend fun getMovie(
        headerMap: Map<String, String>,
        queryMap: Map<String, String>
    ): Response<NaverMovie> {
        return remoteDataSource.getMovie(headerMap, queryMap)
    }

    override suspend fun addLog(msg: String) {
        localDataSource.addLog(msg)
    }

    override suspend fun getAllLogs(): List<Log> = localDataSource.getAllLogs()

    override suspend fun removeLogs() {
        localDataSource.removeLogs()
    }

    override suspend fun getLastLog(): Log? = localDataSource.getLastLog()
}
