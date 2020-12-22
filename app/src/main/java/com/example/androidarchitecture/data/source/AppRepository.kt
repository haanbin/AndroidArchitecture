package com.example.androidarchitecture.data.source

import com.example.androidarchitecture.data.entities.RandomUser
import com.example.androidarchitecture.data.source.local.LocalDataSource
import com.example.androidarchitecture.data.source.remote.RemoteDataSource
import com.example.androidarchitecture.di.Local
import com.example.androidarchitecture.di.Remote
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AppDataSource {

    override fun getRandomUser(queryMap: Map<String, String>): Single<RandomUser> {
        return remoteDataSource.getRandomUser(queryMap)
    }

    companion object {

        private var INSTANCE: AppRepository? = null

        @JvmStatic
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ) = INSTANCE ?: synchronized(AppRepository::class.java) {
            INSTANCE ?: AppRepository(remoteDataSource, localDataSource)
                .also { INSTANCE = it }
        }
    }
}