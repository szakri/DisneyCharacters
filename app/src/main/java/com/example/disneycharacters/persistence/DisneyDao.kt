package com.example.disneycharacters.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DisneyDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<CharacterEntity>>

    @Insert
    suspend fun insertCharacter(character: CharacterEntity) : Long

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
}