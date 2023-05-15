package com.example.disneycharacters.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun disneyDao(): DisneyDao
}