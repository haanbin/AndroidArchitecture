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
import javax.inject.Qualifier
import javax.inject.Singleton
//
//@Qualifier
//annotation class Local
//
//@Qualifier
//annotation class Remote
//
//@InstallIn(ApplicationComponent::class)
//@Module
//abstract class LocalDataSourceModule {
//
//    @Local
//    @Singleton
//    @Binds
//    abstract fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource
//}
//
//@InstallIn(ApplicationComponent::class)
//@Module
//abstract class RemoteDataSourceModule {
//
//    @Remote
//    @Singleton
//    @Binds
//    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
//}
//
//@InstallIn(ApplicationComponent::class)
//@Module
//abstract class RepositoryModule {
//
//    @Remote
//    @Singleton
//    @Binds
//    abstract fun bindRepository(impl: AppRepository): AppDataSource
//}