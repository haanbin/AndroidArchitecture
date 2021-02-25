package com.test.data.di

import android.content.Context
import androidx.room.Room
import com.test.data.local.AppDatabase
import com.test.data.local.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "TEST_DATABASE"
        ).build()

    @Provides
    fun provideLogDao(database: AppDatabase): LogDao = database.logDao()
}