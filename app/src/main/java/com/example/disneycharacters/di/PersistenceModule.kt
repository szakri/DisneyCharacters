package com.example.disneycharacters.di

import android.content.Context
import androidx.room.Room
import com.example.disneycharacters.persistence.AppDatabase
import com.example.disneycharacters.persistence.DisneyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "disney_characters"
    ).build()

    @Provides
    fun provideDisneyDao(database: AppDatabase): DisneyDao {
        return database.disneyDao()
    }
}