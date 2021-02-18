package com.example.androidarchitecture.data.source

import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.data.entities.NaverMovie
import com.example.androidarchitecture.data.entities.RandomUser
import com.example.androidarchitecture.data.source.local.LocalDataSourceImpl
import com.example.androidarchitecture.data.source.remote.RemoteDataSourceImpl
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