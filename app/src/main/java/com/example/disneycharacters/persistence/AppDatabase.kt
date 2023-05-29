package com.example.disneycharacters.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.disneycharacters.model.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun disneyDao(): DisneyDao
}