package com.example.androidarchitecture.data.source

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

    companion object {

        private var INSTANCE: AppRepository? = null

        @JvmStatic
        fun getInstance(
            remoteDataSourceImpl: RemoteDataSourceImpl,
            localDataSourceImpl: LocalDataSourceImpl
        ) = INSTANCE ?: synchronized(AppRepository::class.java) {
            INSTANCE ?: AppRepository(remoteDataSourceImpl, localDataSourceImpl)
                .also { INSTANCE = it }
        }
    }
}