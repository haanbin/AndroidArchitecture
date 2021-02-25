package com.test.data.di

import com.test.data.AppRepository
import com.test.data.local.LocalDataSource
import com.test.data.local.LocalDataSourceImpl
import com.test.data.remote.RemoteDataSource
import com.test.data.remote.RemoteDataSourceImpl
import com.test.domain.AppDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class DataSourceModule{

    @Binds
    abstract fun bindAppRepository(appRepository: AppRepository): AppDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

}