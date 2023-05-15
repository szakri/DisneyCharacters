package com.example.disneycharacters.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val url: String,
    val name: String,
    val sourceUrl: String,
    val films: String,
    val shortFilms: String,
    val tvShows: String,
    val videoGames: String,
    val parkAttractions: String
)
