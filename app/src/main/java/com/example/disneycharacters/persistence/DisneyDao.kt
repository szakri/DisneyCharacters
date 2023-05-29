package com.example.disneycharacters.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.disneycharacters.model.Character

@Dao
interface DisneyDao {
    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<Character>

    @Query("SELECT COUNT(*) FROM characters WHERE _id = :id")
    suspend fun characterExists(id: Int): Boolean

    @Insert
    suspend fun insertCharacter(character: Character) : Long

    @Delete
    suspend fun deleteCharacter(character: Character)
}