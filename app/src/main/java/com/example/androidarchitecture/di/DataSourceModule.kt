package com.example.androidarchitecture.di

import com.example.androidarchitecture.data.source.AppDataSource
import com.example.androidarchitecture.data.source.local.LocalDataSource
import com.example.androidarchitecture.data.source.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class Local

@Qualifier
annotation class Remote

@InstallIn(ApplicationComponent::class)
@Module
abstract class LocalDataSourceModule {

    @Local
    @Singleton
    @Binds
    abstract fun bindLocalDataSource(impl: LocalDataSource): AppDataSource
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class RemoteDataSourceModule {

    @Remote
    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(impl: RemoteDataSource): AppDataSource
}