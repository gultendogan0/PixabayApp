package com.gultendogan.pixabayapp.di

import android.content.Context
import androidx.room.Room
import com.gultendogan.pixabayapp.data.repository.FavoriteRepository
import com.gultendogan.pixabayapp.data.room.PixabayDao
import com.gultendogan.pixabayapp.data.room.PixabayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @InstallIn(SingletonComponent::class)
    @Module
    class DatabaseModule {
        @Provides
        fun provideChannelDao(pixabayDatabase: PixabayDatabase): PixabayDao  =
            pixabayDatabase.pixebayDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): PixabayDatabase =
        Room.databaseBuilder(
            appContext,
            PixabayDatabase::class.java,
            "pixebay_database"
        ).build()


    @Provides
    @Singleton
    fun provideFavoriteRepository(pixabayDao: PixabayDao): FavoriteRepository =
        FavoriteRepository(pixabayDao)
}