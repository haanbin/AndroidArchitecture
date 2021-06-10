package com.example.androidarchitecture.di

import com.example.androidarchitecture.data.source.AppDataSource
import com.example.androidarchitecture.data.source.AppRepository
import com.example.androidarchitecture.data.source.local.LocalDataSource
import com.example.androidarchitecture.data.source.local.LocalDataSourceImpl
import com.example.androidarchitecture.data.source.remote.RemoteDataSource
import com.example.androidarchitecture.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindRepository(impl: AppRepository): AppDataSource
}
